package dev.cstv.musify.data;

import dev.cstv.musify.domain.*;
import dev.cstv.musify.messaging.mail.MailTask;
import dev.cstv.musify.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class TestData {

    @Autowired
    SongService songService;
    @Autowired
    ArtistService artistService;
    @Autowired
    GenreService genreService;
    @Autowired
    UserService userService;
    @Autowired
    ChartService chartService;

    @Autowired
    GroupService groupService;

    public void load() {

        Artist artist = new Artist("Khaled");
        Artist sautiSol = new Artist("Sauti Sol");
        Artist lievTuk = new Artist("Liev Tuk");
        Artist thuyChi = new Artist("Thuy Chi");

        Genre popGenre = new Genre("Pop");
        Genre soulGenre = new Genre("Soul");
        Genre rockGenre = new Genre("Rock");
        Genre jazzGenre = new Genre("Jazz");
        Genre bluesGenre = new Genre("Blues");
        Genre classicalGenre = new Genre("Classical Music");
        Genre hiphopGenre = new Genre("Hip Hop");

        //Add All other genres
        List<Genre> genres = new ArrayList<>();
        genres.add(soulGenre);
        genres.add(popGenre);
        genres.add(rockGenre);
        genres.add(jazzGenre);
        genres.add(bluesGenre);
        genres.add(classicalGenre);
        genres.add(hiphopGenre);

        genres.forEach(genre -> genreService.save(genre));

        //getAllGenres
        genres = genreService.findAll();

        /*
        Create new Songs
         */
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2013);
        calendar.set(Calendar.MONTH, 12);
        calendar.set(Calendar.DAY_OF_MONTH, 2);

        Song song = new Song.Builder()
                .setTitle("Better")
                .setGenre(genres.get(0))
                .setDuration(3)
                .setUrl("http://127.0.0.1:5500/resources/music/3.mp3")
                .setReleaseDate(calendar.getTime())
                .setArtist(artist)
                .build();

        Song cSong = new Song.Builder()
                .setTitle("Short N' Sweet")
                .setGenre(genres.get(1))
                .setDuration(4)
                .setUrl("http://127.0.0.1:5500/resources/music/4.mp3")
                .setReleaseDate(calendar.getTime())
                .setArtist(sautiSol)
                .build();

        Song vSong = new Song.Builder()
                .setTitle("One love")
                .setGenre(genres.get(1))
                .setDuration(4)
                .setUrl("http://127.0.0.1:5500/resources/music/1.mp3")
                .setReleaseDate(calendar.getTime())
                .setArtist(thuyChi)
                .build();

        Song sautiSong = new Song.Builder()
                .setTitle("Fall")
                .setGenre(genres.get(1))
                .setDuration(3)
                .setUrl("http://127.0.0.1:5500/resources/music/2.mp3")
                .setReleaseDate(calendar.getTime())
                .setArtist(sautiSol)
                .build();

        //Songs in Album
        Song secondSong = new Song.Builder()
                .setTitle("Going Raggae")
                .setGenre(genres.get(4))
                .setDuration(3)
                .setUrl("http://127.0.0.1:5500/resources/music/1.mp3")
                .setReleaseDate(calendar.getTime())
                .setArtist(artist)
                .build();

        Song thirdSong = new Song.Builder()
                .setTitle("Lagos")
                .setGenre(genres.get(6))
                .setDuration(3)
                .setUrl("http://127.0.0.1:5500/resources/music/2.mp3")
                .setReleaseDate(calendar.getTime())
                .setArtist(lievTuk)
                .build();

        //Add BatchSong to Artist Object
        artist.addSong(song);
        sautiSol.addSong(sautiSong);
        thuyChi.addSong(vSong);
        sautiSol.addSong(cSong);
        artist.addSong(secondSong);
        lievTuk.addSong(thirdSong);

        //songService.save(virtualSong);
        Calendar albumCalendar = Calendar.getInstance();
        albumCalendar.set(2018, Calendar.DECEMBER, 3);
        //Add Album to Artist Object
        Album album = new Album("My First Album", albumCalendar.getTime());
        // album.addSong(secondSong);
        albumCalendar.set(2019, Calendar.DECEMBER, 8);
        Album secondAlbum = new Album("My Second Album", albumCalendar.getTime());
        //  secondAlbum.addSong(thirdSong);
        secondAlbum.addSong(song);

        artist.addAlbum(album);
        artist.addAlbum(secondAlbum);

        //Save Artist(s)
        artistService.save(artist);
        artistService.save(thuyChi);
        artistService.save(lievTuk);
        artistService.save(sautiSol);

        /*
          Create a chart
         */
        Chart chart = new Chart("Top 50 Iowa Hits");
        ChartSong chartSong = new ChartSong(chart, vSong);
        ChartSong chartSong2 = new ChartSong(chart, cSong);
        ChartSong chartSong3 = new ChartSong(chart, sautiSong);

        chart.addSong(chartSong);
        chart.addSong(chartSong2);
        chart.addSong(chartSong3);

        chartService.save(chart);

        /*
         * Init group user and authorities
         * */
        Group groupUser = new Group();
        groupUser.setName("User");

        Authority authority = new Authority();
        authority.setAuthority("create");
        groupUser.getAuthority().add(authority);

        authority = new Authority();
        authority.setAuthority("update");
        groupUser.getAuthority().add(authority);

        authority = new Authority();
        authority.setAuthority("delete");
        groupUser.getAuthority().add(authority);

        authority = new Authority();
        authority.setAuthority("list");
        groupUser.getAuthority().add(authority);

        /*
        /Dummy data for Users
        Create a new User
        */

        User user = new User("Steven", "Katabalwa");
        UserCredentials stevenCredentials = new UserCredentials(user, "stev", "stev", "stek32@gmail.com");
        user.setUserCredentials(stevenCredentials);
        userService.saveFull(user);

        User secondUser = new User("Vorleak", "Chy");
        UserCredentials vorleakCredentials = new UserCredentials(secondUser, "vorleak", "vorleak", "vorleak.chy@gmail.com");
        secondUser.setUserCredentials(vorleakCredentials);
        userService.saveFull(secondUser);

        User charlo = new User("Charles", "Muchene");
        UserCredentials charlesCredentials = new UserCredentials(charlo, "charlo", "charlo", "senseidev@gmail.com");
        charlo.setUserCredentials(charlesCredentials);
        userService.saveFull(charlo);

        User fourthUser = new User("Tuy", "Vo");
        UserCredentials tuyCredentials = new UserCredentials(fourthUser, "tuy", "tuy", "tuy@mail.com");
        fourthUser.setUserCredentials(tuyCredentials);
        userService.saveFull(fourthUser);

        UserCredentials johnUserCredentials = new UserCredentials("john", "john", "john@musify.com");
        User johnUser = new User("John", "Smith", johnUserCredentials);
        userService.saveFull(johnUser);

        UserCredentials paulUserCredentials = new UserCredentials("paul", "paul", "paul@musify.com");
        User paulUser = new User("Paul", "Smith", paulUserCredentials);
        userService.saveFull(paulUser);

        groupUser.getUserCredentials().add(stevenCredentials);
        groupUser.getUserCredentials().add(vorleakCredentials);
        groupUser.getUserCredentials().add(charlesCredentials);
        groupUser.getUserCredentials().add(tuyCredentials);
        groupUser.getUserCredentials().add(johnUserCredentials);
        groupUser.getUserCredentials().add(paulUserCredentials);

        /*
        Create a playList for a user
         */
        Playlist playlist = new Playlist("Steven's Playlist", user);

        List<Song> songs = songService.findAll();

        songs.forEach(playlist::addSong);

        user.addPlaylist(playlist);

        userService.update(user);

        Playlist charloPlaylist = new Playlist("Charlo's Playlist", charlo);

        Song fSong = songService.findOne(2);

        charloPlaylist.addSong(fSong);

        charlo.getPlaylists().add(charloPlaylist);

        userService.update(charlo);

        /*
         * Create user group
         * */
        groupService.save(groupUser);

        System.out.println("*** Loaded Dummy Data ***");

    }
}
