import java.util.*;

public class MusicPlayer {
    private static Scanner scanner = new Scanner(System.in); // put it in class
    private ArrayList<Album> myAlbums = new ArrayList<>();
    private LinkedList<String> myPlayList = new LinkedList<>();
//    private ListIterator<String> playListListIterator = myPlayList.listIterator();
//    private boolean quit = false;
//    private boolean goingForward = true;


    public MusicPlayer(String playerName) {

        System.out.println("Hi " + playerName + "! ");
        boolean quit = false;
        int choice = 0;
        printInstructions();
        while (!quit) {
            System.out.println("Enter your choice");
            choice = scanner.nextInt();
            scanner.nextLine();
            printInstructions();
            switch (choice) {
                case 0:
                    printInstructions();
                    break;
                case 1:
                    printPlayList();
                    break;
                case 2:
                    addSongsToPlayList();
                    break;
                case 3:
                    createAlbum();
                    break;
                case 4:
                    addSongToAlbum();
                    break;
                case 5:
                    playPlayList();
                    break;
//                case 6:
//                    nextSong();
//                    break;
//                case 7:
//                    previousSong();
//                    break;
//                case 8:
//                    repeatSong();
//                    break;
//                case 9:
//                    removeSong();
//                    break;
                case 10:
                    quit = true;
                    break;
            }
        }
    }

    private void printInstructions() {
        System.out.println("\n Press");
        System.out.println("\t 0 - Print choice options");
        System.out.println("\t 1 - Print play list");
        System.out.println("\t 2 - Add song from existing album to play list");
        System.out.println("\t 3 - Create album");
        System.out.println("\t 4 - Add song to existing album");
//        System.out.println("\t =====");
        System.out.println("\t 5 - play list");
//        System.out.println("\t 6 - Next song");
//        System.out.println("\t 7 - Previous song");
//        System.out.println("\t 8 - Repeat current song");
//        System.out.println("\t 9 - remove current song");
//        System.out.println("\t =====");
        System.out.println("\t 10 - To quit the application");
    }

    private void printPlayMenu() {
        System.out.println("\n Press");
        System.out.println("\t 0 - Print play menu");
        System.out.println("\t 6 - Next song");
        System.out.println("\t 7 - Previous song");
        System.out.println("\t 8 - Repeat current song");
        System.out.println("\t 9 - remove current song");
        System.out.println("\t =====");
        System.out.println("\t 10 - To quit the application");
    }

    private void printPlayList() {
        System.out.println("Print play list");
        if (myPlayList.size() > 0) {
            System.out.println("My Play List: ");
            for (int i = 0; i < myPlayList.size(); i++) {
                System.out.println((i + 1) + ". " + myPlayList.get(i));
            }
        } else {
            System.out.println("No songs in the playlist");
        }
    }

    private void addSongsToPlayList() {
        printAlbums();
        System.out.println("Please enter the existing album name: ");
        String name = scanner.nextLine();
        Album album = findAlbum(name);
        if (album != null) {
            album.printSongs();
            System.out.println("Enter the song name to add to playList: ");
            String songName = scanner.nextLine();
            if (album.findSong(songName) != -1) {
                if (checkSong(songName)) {
                    System.out.println("\"" + songName + "\" already exists in the playlist");
                } else {
                    myPlayList.add(songName);
                }
            } else {
                System.out.println(" \"" + songName + "\" is not existing in this album !");
            }
        } else {
            System.out.println("Album is not existing");
        }
    }

    private void createAlbum() {
        printAlbums();
        System.out.println("Create a new Album name : ");
        String name = scanner.nextLine();
        if (findAlbum(name) == null) {
            myAlbums.add(new Album(name));
        } else {
            System.out.println("Album is already existing");
        }
    }

    private void printAlbums() {
        if (myAlbums.size() > 0) {
            System.out.println("Existing Albums:");
            for (int i = 0; i < myAlbums.size(); i++) {
                System.out.println((i + 1) + ". " + myAlbums.get(i).getAlbumName());
            }
        }
    }

    private void addSongToAlbum() {
        printAlbums();
//        System.out.println("Please enter the existing album name: ");
        System.out.println("Please enter the existing album name: ");
        int albumNumber = scanner.nextInt();
        scanner.nextLine();
//        String albumName = scanner.nextLine();
        Album album = myAlbums.get(albumNumber - 1);

//        Album album = findAlbum(albumName);
//        System.out.println("albums = " + album.toString()); // for test
        if (album != null) {
            album.printSongs();
            System.out.println("Enter the song name to add: ");
            String songName = scanner.nextLine();
            album.addSong(songName);
            System.out.println("\"" + songName + "\" is added in playlist ");
        } else {
            System.out.println("Album is not existing");
        }

    }

    private void playPlayList() {
        ListIterator<String> playListListIterator = myPlayList.listIterator();
        boolean quit = false;
        boolean goingForward = true;
        if (myPlayList.isEmpty()) {
            System.out.println("Playlist doesn't contain any songs");
        } else {
            printPlayMenu();
            System.out.println("Now Playing ---> " + playListListIterator.next());
        }
        while (!quit) {
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 0:
                    printPlayMenu();
                case 6: // next song
                    if (!goingForward) {
                        if (playListListIterator.hasNext()) {
                            playListListIterator.next();
                        }
                        goingForward = true;
                    }
                    if (playListListIterator.hasNext()) {
                        System.out.println("Now \"" + playListListIterator.next() + "\" is playing");
                    } else {
                        System.out.println("Reached the end of the playlist");
                        goingForward = false;
                    }
                    break;
                case 7: // previous
                    if (goingForward) {
                        if (playListListIterator.hasPrevious()) {
                            playListListIterator.previous();
                            goingForward = false;
                        }
                    }
                    if (playListListIterator.hasPrevious()) {
                        System.out.println("Now \"" + playListListIterator.previous() + "\" is playing");
                    } else {
                        System.out.println("Reached the start of the playlist");
                        goingForward = true;
                    }
                    break;
                case 8: // repeat
                    if (goingForward) {
                        if (playListListIterator.hasPrevious()) {
                            System.out.println("Now replay\"" + playListListIterator.previous() + "\""); // print then move to previous
                            goingForward = false;
                        } else {
                            System.out.println("Reached the start of the playlist");
                        }
                    } else {
                        if (playListListIterator.hasNext()) {
                            System.out.println("Now replay (N)\"" + playListListIterator.next() + "\""); // move to next then print
                            goingForward = true;
                        } else {
                            System.out.println("Reached the end of the playlist");
                        }
                    }
//                    System.out.println("goingForward " + goingForward);
                    break;
                case 9: // remove
                    playListListIterator.remove();
                    System.out.println("The song is removed");
                    if (playListListIterator.hasNext())
                        System.out.println("Now Playing ---> " + playListListIterator.next());
                    else if (playListListIterator.hasPrevious())
                        System.out.println("Now Playing ---> " + playListListIterator.previous());
                    break;
                case 10:
                    quit = true;
                    break;
            }
//            System.out.println("goingForward " + goingForward);
        }

    }

//    private void nextSong() {
//        System.out.println("next song");
//        if (playListListIterator.hasNext()) {
//            System.out.println("Now \"" + playListListIterator.next() + "\" is playing");
//        } else {
//            System.out.println("Reached the end of the playlist");
////            this.goingForward = false;
//        }
//    }
//
//    private void previousSong() {
//
//    }
//
//    private void repeatSong() {
//
//    }
//
//    private void removeSong() {
//
//    }

    private Album findAlbum(String name) {
        for (Album album : this.myAlbums) {
            if (album.getAlbumName().toLowerCase().equals(name.toLowerCase())) {
//                System.out.println("current Album name: " + album.toString()); // test
                return album;
            }
        }
        return null;
    }

    private boolean checkSong(String songName) {
        Iterator<String> i = myPlayList.iterator();
        while (i.hasNext()) {
            if (i.next().toLowerCase().equals(songName.toLowerCase())) {
                System.out.println("has song already");
                return true;
            }
        }
        return false;
    }
}
