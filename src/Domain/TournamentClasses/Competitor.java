package Domain.TournamentClasses;

public class Competitor {
    private String name;
    private int id;
    private Double time;
    public Competitor(String name, int id, Double time) {
        this.id = id;
        this.name = name;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public Double getTime() {
        return time;
    }

    public String getCompact()
    {
        return name +";"+ id +";"+ time;
    }

    public String toString()
    {
        return name + " time: " + time;
    }
}
