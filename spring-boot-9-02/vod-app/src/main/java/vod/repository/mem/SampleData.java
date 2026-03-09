package vod.repository.mem;

import vod.model.Employee;
import vod.model.Manga;
import vod.model.MangaShop;

import java.util.ArrayList;
import java.util.List;

class SampleData {

    static List<MangaShop> mangaShops = new ArrayList<>();

    static List<Employee> employees = new ArrayList<>();

    static List<Manga> mangas = new ArrayList<>();

    static {

        Employee oda = new Employee(1, "Eiichiro", "Oda");
        Employee toriyama = new Employee(2, "Akira", "Toriyama");
        Employee arakawa = new Employee(3, "Hiromu", "Arakawa");
        Employee miura = new Employee(4, "Kentaro", "Miura");

        Manga onePiece = new Manga(1, "One Piece",
                "https://example.com/one-piece.jpg", oda, 4.9f);
        Manga onePieceStrongWorld = new Manga(2, "One Piece: Strong World",
                "https://example.com/one-piece-strong-world.jpg", oda, 4.6f);

        Manga dragonBall = new Manga(3, "Dragon Ball",
                "https://example.com/dragon-ball.jpg", toriyama, 4.5f);
        Manga dragonBallZ = new Manga(4, "Dragon Ball Z",
                "https://example.com/dragon-ball-z.jpg", toriyama, 4.7f);

        Manga fullmetal = new Manga(5, "Fullmetal Alchemist",
                "https://example.com/fullmetal.jpg", arakawa, 4.8f);
        Manga fullmetalBrotherhood = new Manga(6, "Fullmetal Alchemist: Brotherhood",
                "https://example.com/fullmetal-brotherhood.jpg", arakawa, 4.9f);

        Manga berserk = new Manga(7, "Berserk",
                "https://example.com/berserk.jpg", miura, 5.0f);
        Manga berserkGoldenAge = new Manga(8, "Berserk: Golden Age Arc",
                "https://example.com/berserk-golden-age.jpg", miura, 4.7f);


        bind(onePiece, oda);
        bind(onePieceStrongWorld, oda);

        bind(dragonBall, toriyama);
        bind(dragonBallZ, toriyama);

        bind(fullmetal, arakawa);
        bind(fullmetalBrotherhood, arakawa);

        bind(berserk, miura);
        bind(berserkGoldenAge, miura);




        MangaShop mangaWorld = new MangaShop(1, "Manga World",
                "https://example.com/manga-world-logo.png");
        MangaShop otakuShop = new MangaShop(2, "Otaku Shop",
                "https://example.com/otaku-shop-logo.png");
        MangaShop shonenHouse = new MangaShop(3, "Shonen House",
                "https://example.com/shonen-house-logo.png");
        MangaShop darkMangaStore = new MangaShop(4, "Dark Manga Store",
                "https://example.com/dark-manga-store-logo.png");

        bind(mangaWorld, onePiece);
        bind(shonenHouse, onePiece);
        bind(shonenHouse, dragonBall);
        bind(shonenHouse, dragonBallZ);

        bind(otakuShop, fullmetal);
        bind(darkMangaStore, fullmetal);
        bind(darkMangaStore, berserk);
        bind(otakuShop, berserk);
        bind(otakuShop, berserkGoldenAge);

        mangas.add(onePiece);
        mangas.add(onePieceStrongWorld);
        mangas.add(dragonBall);
        mangas.add(dragonBallZ);
        mangas.add(fullmetal);
        mangas.add(fullmetalBrotherhood);
        mangas.add(berserk);
        mangas.add(berserkGoldenAge);

        employees.add(oda);
        employees.add(toriyama);
        employees.add(arakawa);
        employees.add(miura);

        mangaShops.add(mangaWorld);
        mangaShops.add(otakuShop);
        mangaShops.add(shonenHouse);
        mangaShops.add(darkMangaStore);


    }

    private static void bind(MangaShop c, Manga m) {
        c.addManga(m);
        m.addMangaShop(c);
    }

    private static void bind(Manga m, Employee d) {
        d.addManga(m);
        m.setEmployee(d);
    }

}
