package Main;

public class ShowInfo {

    public String title;
    public int episode, season;

    public ShowInfo(){
        title = null;
        season = 0;
        episode = 0;
    }

    ShowInfo(String title, int episode){
        this.title = title;
        this.episode = episode;
    }

    public ShowInfo(String title, int season, int episode){
        this.title = title;
        this.season = season;
        this.episode = episode;
    }

    public String getTitle() {
        return title;
    }

    public int getSeason() {
        return season;
    }

    public int getEpisode() {
        return episode;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSeason(int season) {
        this.season = season;
    }

    public void setEpisode(int episode) {
        this.episode = episode;
    }

    @Override
    public String toString() {
        return title;
    }
}
