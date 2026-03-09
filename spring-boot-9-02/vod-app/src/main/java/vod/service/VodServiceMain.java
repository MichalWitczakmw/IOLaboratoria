package vod.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import vod.repository.MangaShopDao;
import vod.repository.MangaDao;
import vod.repository.mem.MemMangaShopDao;
import vod.repository.mem.MemMangaDao;
import vod.model.MangaShop;

import java.util.List;

public class VodServiceMain {

    public static void main(String[] args) {
        System.out.println("Let's find mangaShops!");
        MangaShopDao mangaShopDao = new MemMangaShopDao();
        MangaDao mangaDao = new MemMangaDao();

        ApplicationContext context = new AnnotationConfigApplicationContext("vod");
        //MangaShopService service = new MangaShopServiceBean(mangaShopDao, mangaDao);
        MangaShopService service = context.getBean(MangaShopService.class);
        MangaShopService service2 = context.getBean(MangaShopService.class);


        List<MangaShop> mangaShops = service.getAllMangaShops();
        System.out.println(mangaShops.size() + " mangaShops found:");
        mangaShops.forEach(System.out::println);

        String foo = context.getBean(String.class);
        System.out.println("foo string: " + foo);
    }
}
