package vod.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

public class Manga {

    private int id;
    private String title;
    private String poster;
    private Employee employee;
    private float rating;
    @JsonIgnore
    private List<MangaShop> mangaShops = new ArrayList<>();

    public Manga(int id, String title, String poster, Employee employee, float rating) {
        this.id = id;
        this.title = title;
        this.poster = poster;
        this.employee = employee;
        this.rating = rating;
    }

    public Manga() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public List<MangaShop> getMangaShops() {
        return mangaShops;
    }

    public void setMangaShops(List<MangaShop> mangaShops) {
        this.mangaShops = mangaShops;
    }

    public void addMangaShop(MangaShop mangaShop) {
        this.mangaShops.add(mangaShop);
    }

    @Override
    public String toString() {
        return "Manga{" +
                "title='" + title + '\'' +
                ", employee=" + employee +
                ", rating=" + rating +
                '}';
    }
}
