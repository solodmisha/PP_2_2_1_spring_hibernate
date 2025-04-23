package hiber.model;

import javax.persistence.*;

@Entity
@Table(name = "cars")
public class Car {
    @Id
    @Column(name = "user_id")
    private Long id;

    @Column(name = "model")
    private String model;

    @Column(name = "series")
    private Long series;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

    public Car() {
    }

    public Car(String model, Long series) {
        this.model = model;
        this.series = series;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Long getSeries() {
        return series;
    }

    public void setSeries(Long series) {
        this.series = series;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Model: " + getModel() + "; Series:" + getSeries();
    }





}
