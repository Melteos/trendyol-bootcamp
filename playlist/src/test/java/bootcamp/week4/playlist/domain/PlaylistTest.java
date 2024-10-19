package bootcamp.week4.playlist.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class PlaylistTest {

    @Test
    public void addTrack_WhenTrackIsAdded_ShouldIncrementTrackCount() {
        //Arrange
        User user = new User("Meltem", "S");
        Playlist sut = new Playlist("Study Mix", "Relaxing songs to listen when studying", user.getId());
        Track track = new Track("Hanoi Cafe", "232 seconds", "Blue Toucan");

        //Act
        sut.addTrack(track);

        //Assert
        assertThat(sut.getTrackCount()).isEqualTo(1);
    }

    @Test
    public void removeTrack_WhenTrackIsRemoved_ShouldDecrementTrackCount() {
        //Arrange
        User user = new User("Meltem", "S");
        Playlist sut = new Playlist("Study Mix", "Relaxing songs to listen when studying", user.getId());
        Track track = new Track("Hanoi Cafe", "232 seconds", "Blue Toucan");
        sut.addTrack(track);

        //Act
        sut.removeTrack(1);

        //Assert
        assertThat(sut.getTrackCount()).isEqualTo(0);
    }

    @Test
    public void removeTrack_WhenTrackIndexIsNotInRange_ShouldThrowException() {
        //Arrange
        User user = new User("Meltem", "S");
        Playlist sut = new Playlist("Study Mix", "Relaxing songs to listen when studying", user.getId());
        Track track = new Track("Hanoi Cafe", "232 seconds", "Blue Toucan");
        sut.addTrack(track);

        //Act
        Throwable throwable = catchThrowable( () -> sut.removeTrack(5) );

        //Assert
        assertThat(throwable).isInstanceOf(IndexOutOfBoundsException.class).hasMessage("track with that index not found");
    }
}
