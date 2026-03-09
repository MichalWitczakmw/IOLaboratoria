package vod.web.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import vod.model.Manga;
import vod.model.MangaShop;
import vod.service.MangaShopService;
import vod.service.MangaService;
import vod.web.rest.dto.MangaDTO;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/webapi")
public class MangaRest {

    private final MangaShopService mangaShopService;
    private final MangaService mangaService;

    // GET wszystkich mang
    @GetMapping("/mangas")
    List<Manga> getMangas() {
        log.info("about to retrieve mangas list");
        List<Manga> mangas = mangaService.getAllMangas();
        log.info("{} mangas found", mangas.size());
        return mangas;
    }

    // GET manga po id
    @GetMapping("/mangas/{id}")
    ResponseEntity<Manga> getManga(@PathVariable("id") int id) {
        log.info("about to retrieve manga {}", id);
        Manga manga = mangaService.getMangaById(id);
        log.info("manga found: {}", manga);
        if (manga != null) {
            return ResponseEntity.ok(manga);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // GET mange dostępne w danym sklepie
    @GetMapping("/manga-shops/{mangaShopId}/mangas")
    ResponseEntity<List<Manga>> getMangasInMangaShop(
            @PathVariable("mangaShopId") int mangaShopId) {
        log.info("about to retrieve mangas available in mangaShop {}", mangaShopId);
        MangaShop mangaShop = mangaShopService.getMangaShopById(mangaShopId);
        if (mangaShop == null) {
            return ResponseEntity.notFound().build();
        } else {
            List<Manga> mangas = mangaShopService.getMangasInMangaShop(mangaShop);
            log.info("there's {} mangas available in mangaShop {}", mangas.size(), mangaShop.getName());
            return ResponseEntity.ok(mangas);
        }
    }

    // POST - dodaj nową mangę
    @PostMapping("/mangas")
    ResponseEntity<?> addManga(@RequestBody MangaDTO mangaDTO) {
        log.info("about to add new manga {}", mangaDTO);
        Manga manga = new Manga();
        manga.setTitle(mangaDTO.getTitle());
        manga.setPoster(mangaDTO.getPoster());
        manga.setRating(mangaDTO.getRating());
        manga.setEmployee(mangaService.getEmployeeById(mangaDTO.getEmployeeId()));

        manga = mangaService.addManga(manga);
        log.info("new manga added: {}", manga);

        return ResponseEntity
                .created(
                        ServletUriComponentsBuilder
                                .fromCurrentRequestUri()
                                .path("/" + manga.getId())
                                .build()
                                .toUri())
                .body(manga);
    }
}
