import java.util.LinkedList;
import java.util.Scanner;

public class Album {
    private String albumName;
    private LinkedList<Song> songList = new LinkedList<Song>();
    private static Scanner scanner = new Scanner(System.in);

    public Album(String albumName) {
        this.albumName = albumName;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void addSong(String songName) {
//        System.out.println("song name " + songName);
        int position = findSong(songName);
        if (position == -1) {
            System.out.println(" Enter the song's duration (s): ");
            int duration = scanner.nextInt();
            songList.add(new Song(songName, duration));
        } else {
            System.out.println("This song is already existing ");
        }
    }

    public void printSongs() {
        if (songList.size() > 0) {
            System.out.println("Albums: \"" + this.albumName + "\""); // how people know current album name?
            for (int i = 0; i < songList.size(); i++) {
                System.out.println((i + 1) + ". " + songList.get(i).getTitle() + "(" + songList.get(i).getDuration() + " s)");
            }
        }
    }

    public int findSong(String name) {
        for (int i = 0; i < songList.size(); i++) {
            Song song = songList.get(i);
            if (song.getTitle().toLowerCase().equals(name.toLowerCase())) {
                return i;
            }
        }
        return -1;
    }


    @Override
    public String toString() { //  for testing
        return this.albumName;
    }
}