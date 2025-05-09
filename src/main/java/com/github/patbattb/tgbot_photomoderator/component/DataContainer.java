package com.github.patbattb.tgbot_photomoderator.component;

import com.github.patbattb.tgbot_photomoderator.service.json.Exclude;
import com.github.patbattb.tgbot_photomoderator.service.json.JsonHandler;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public final class DataContainer {

    @Getter
    @Setter
    @SuppressWarnings("InstantiationOfUtilityClass")
    private static Container container = new Container();


    /**
     * Method for initialize static Container
     */
    public static void init() {
        JsonHandler.loadData();
    }

    public static class Container {
        @SuppressWarnings("FieldMayBeFinal") //Makes not final for GSON lib
        @SerializedName("group")
        private static Map<UserGroup, Set<String>> userGroupMap;
        @Getter
        private static BotState botState;
        @Getter
        private static String channel;
        private static Location location;
        @SuppressWarnings("FieldMayBeFinal")
        private static Set<User> userList;
        @SuppressWarnings("FieldMayBeFinal")
        private static Set<Photo> photoList;
        @Exclude
        private static Location tempLocation;
        @Exclude
        private final static long DAYS_TO_REMOVE_PHOTO = 5;
        @Exclude
        private final static Set<UserGroup> authorizerGroups = Set.of(UserGroup.ADMIN, UserGroup.MODERATOR);

        static {
            userGroupMap = Map.of(
                    UserGroup.ADMIN, new HashSet<>(),
                    UserGroup.MODERATOR, new HashSet<>(),
                    UserGroup.BANNED, new HashSet<>()
            );
            channel = "";
            userList = new HashSet<>();
            photoList = new HashSet<>();
        }

        public static void setBotState(BotState botState) {
            if (!botState.equals(Container.botState)) {
                Container.botState = botState;
                JsonHandler.saveData();
            }
        }

        public static boolean setUserState(String userId, UserState state) {
            Optional<User> optional = userList.stream()
                    .filter(elem -> Objects.equals(elem.id(), userId))
                    .findAny();
            return optional.filter(user -> addToList(new User.Updater(user).userState(state).update())).isPresent();

        }

        public static void setChannel(String channel) {
            if (!channel.equals(Container.channel)) {
                Container.channel = channel;
                JsonHandler.saveData();
            }
        }

        public static UserState getChatState(String chatId) {
            Optional<UserState> optional = userList.stream()
                    .filter(elem -> Objects.equals(elem.id(), chatId))
                    .map(User::state)
                    .findAny();
            return optional.orElse(UserState.NO_STATE);
        }

        public static User getUser(String userId) {
            for (User user: userList) {
                if (user.id().equals(userId)) return user;
            }
            return null;
        }

        public static UserGroup getUserGroup(String userId) {
            Optional<UserGroup> optional = userGroupMap.entrySet().stream()
                    .filter(elem -> elem.getValue().contains(userId))
                    .map(Map.Entry::getKey)
                    .findAny();
            return optional.orElse(UserGroup.OTHER);
        }

        /**
         * Method returns all IDs from the groups {@link UserGroup#ADMIN} and {@link UserGroup#MODERATOR} exclude ID of a photo sender.
         * The user can't approve photos that he sent himself.
         * @param selfId userID of sender
         * @return authorizer set
         */
        public static Set<String> getAuthorizersIds(String selfId) {
            return userGroupMap.entrySet().stream()
                    .filter(elem -> authorizerGroups.contains(elem.getKey()))
                    .map(Map.Entry::getValue)
                    .flatMap(Collection::stream)
                    .filter(elem -> !elem.equals(selfId))
                    .collect(Collectors.toSet());
        }

        public static boolean findInGroup(UserGroup userGroup, String userName) {
            Optional<String> optional = getId(userName);
            return optional.filter(id -> userGroupMap.get(userGroup).contains(id)).isPresent();
        }

        public static boolean addToGroup(UserGroup userGroup, String userName) {
            Optional<String> optional = getId(userName);
            if (optional.isPresent()) {
                String id = optional.get();
                userGroupMap.values().forEach(elem -> elem.removeIf(id::equals));
                boolean result = userGroupMap.get(userGroup).add(id);
                if (result) JsonHandler.saveData();
                return result;
            }
            return false;
        }

        public static boolean removeFromGroup(UserGroup userGroup, String userName) {
            Optional<String> optional = getId(userName);
            if (optional.isPresent()) {
                String id = optional.get();
                boolean result = userGroupMap.get(userGroup).remove(id);
                if (result) JsonHandler.saveData();
                return result;
            }
            return false;
        }

        public static boolean removeFromGroup(UserGroup userGroup, Long userId) {
            boolean result = userGroupMap.get(userGroup).remove(String.valueOf(userId));
            if (result) JsonHandler.saveData();
            return result;
        }

        public static boolean addToList(User user) {
            Optional<User> optional = Container.userList.stream()
                    .filter(user::equals).findAny();
            if (optional.isPresent() && optional.get().deepEquals(user)) return false;
            userList.remove(user);
            boolean result = userList.add(user);
            if (result) JsonHandler.saveData();
            return result;
        }

        public static boolean addFirstCoordinate(Point point) {
            if (point == null) return false;
            tempLocation = new Location();
            return tempLocation.addCoordinates(point);
        }

        public static boolean addSecondCoordinate(Point point) {
            if (tempLocation.isReady() || point == null) return false;
            tempLocation.addCoordinates(point);
            if (tempLocation.isReady()) {
                location = tempLocation;
                JsonHandler.saveData();
                return true;
            }
            return  false;
        }

        public static boolean addNewPhoto(Photo photo) {
            cleanUpPhotoList();
            boolean result = photoList.add(photo);
            if (result) JsonHandler.saveData();
            return result;
        }

        public static boolean updatePhoto(Photo photo, PhotoStatus status) {
            Optional<Photo> optional = Container.photoList.stream()
                    .filter(photo::equals).findAny();
            if (optional.isEmpty() || optional.get().status().equals(status)) return false;
            photoList.remove(photo);
            boolean result = photoList.add(new Photo.Updater(photo).setStatus(status).update());
            if (result) JsonHandler.saveData();
            return result;
        }

        public static boolean updatePhoto(Photo photo, Set<AuthMessage> authMessages) {
            Optional<Photo> optional = Container.photoList.stream()
                    .filter(photo::equals).findAny();
            if (optional.isEmpty()) return false;
            photoList.remove(photo);
            boolean result = photoList.add(new Photo.Updater(photo).setAuthMessages(authMessages).update());
            if (result) JsonHandler.saveData();
            return result;
        }

        public static Set<AuthMessage> getAuthMessageSet(String fileId) {
            Optional<Set<AuthMessage>> optional = photoList.stream().filter(elem -> elem.fileId().equals(fileId))
                    .map(Photo::authMessages).findAny();
            return optional.orElse(new HashSet<>());
        }

        public static boolean checkoutPoint(Point point) {
            return location.checkoutPoint(point);
        }

        /**
         * The {@code User.state} field in the {@link User} class is {@link UserState} enum, so it cannot have any value.
         * In case of a data violation in the file, all undefined values for the {@code User.state} field
         * are replaced by the default value {@link UserState#NO_STATE}
         * @return {@code true} if any data has been corrected.
         */
        public static boolean fixIncorrectUserState() {
            boolean result = false;
            Iterator<User> iterator = userList.iterator();
            Set<User> modifiedUsers = new HashSet<>();
            while (iterator.hasNext()) {
                User user = iterator.next();
                if (user.state() == null) {
                    modifiedUsers.add(new User.Updater(user).userState(UserState.NO_STATE).update());
                    iterator.remove();
                    result = true;
                }
            }
            userList.addAll(modifiedUsers);
            return result;
        }

        private static Optional<String> getId(String userName) {
            return userList.stream()
                    .filter(elem -> Objects.equals(elem.userName(), userName))
                    .map(User::id)
                    .findAny();
        }

        private static void cleanUpPhotoList() {
            photoList.removeIf(photo -> photo.sendTime().plusDays(DAYS_TO_REMOVE_PHOTO).isBefore(LocalDateTime.now()));
        }
    }
}
