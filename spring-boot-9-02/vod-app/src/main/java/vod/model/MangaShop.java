package vod.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

public class MangaShop {

    private int id;
    @NotNull
    @Size(min=2, max=20)
    private String name;
    private String logo;
    @JsonIgnore
    private List<Manga> mangas = new ArrayList<>();

    public MangaShop(int id, String name, String logo) {
        this.id = id;
        this.name = name;
        this.logo = logo;
    }

    public MangaShop() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public List<Manga> getMangas() {
        return mangas;
    }

    public void setMangas(List<Manga> mangas) {
        this.mangas = mangas;
    }

    public void addManga(Manga manga) {
        this.mangas.add(manga);
    }

    @Override
    public String toString() {
        return "MangaShop{" +
                "name='" + name + '\'' +
                ", logo='" + logo + '\'' +
                '}';
    }
}
