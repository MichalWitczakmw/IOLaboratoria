package vod.web.rest;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.LocaleResolver;
import vod.model.MangaShop;
import vod.model.Manga;
import vod.service.MangaShopService;
import vod.service.MangaService;

import java.util.List;
import java.util.Locale;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/webapi")
public class MangaShopRest {

    private final MangaShopService mangaShopService;
    private final MangaService mangaService;
    private final MessageSource messageSource;
    private final LocaleResolver localeResolver;
    private final MangaShopValidator validator; // nazwę walidatora zmienimy osobno na MangaShopValidator

    @InitBinder
    void initBinder(WebDataBinder binder) {
        binder.addValidators(validator);
    }

    // GET wszystkie sklepy z mangami (z opcjonalnymi parametrami)
    @GetMapping("/manga-shops")
    List<MangaShop> getMangaShops(
            @RequestParam(value = "phrase", required = false) String phrase,
            @RequestHeader(value = "custom-header", required = false) String customHeader,
            @CookieValue(value = "some-cookie", required = false) String someCookie) {

        log.info("about to retrieve mangaShops list");
        log.info("phrase param: {}", phrase);
        log.info("custom header param: {}", customHeader);
        log.info("some cookie value: {}", someCookie);
        List<MangaShop> mangaShops = mangaShopService.getAllMangaShops();
        log.info("{} mangaShops found", mangaShops.size());
        return mangaShops;
    }

    // GET sklep z mangami po ID
    @GetMapping("/manga-shops/{id}")
    ResponseEntity<MangaShop> getMangaShop(@PathVariable("id") int id) {
        log.info("about to retrieve mangaShop {}", id);
        MangaShop mangaShop = mangaShopService.getMangaShopById(id);
        log.info("mangaShop found: {}", mangaShop);
        if (mangaShop != null) {
            return ResponseEntity.status(200).body(mangaShop);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // GET sklepy sprzedające daną mangę
    @GetMapping("/mangas/{mangaId}/manga-shops")
    ResponseEntity<List<MangaShop>> getMangaShopsSellingManga(
            @PathVariable("mangaId") int mangaId) {

        log.info("about to retrieve manga shops selling manga {}", mangaId);
        Manga manga = mangaService.getMangaById(mangaId);
        if (manga == null) {
            return ResponseEntity.notFound().build();
        } else {
            List<MangaShop> mangaShops = mangaShopService.getMangaShopsByManga(manga);
            log.info("there's {} mangaShops selling manga {}",
                    mangaShops.size(), manga.getTitle());
            return ResponseEntity.ok(mangaShops);
        }
    }

    // POST — dodaj nowy sklep
    @PostMapping("/manga-shops")
    ResponseEntity<?> addMangaShop(
            @Validated @RequestBody MangaShop mangaShop,
            Errors errors,
            HttpServletRequest request) {

        log.info("about to add new mangaShop {}", mangaShop);

        if (errors.hasErrors()) {
            Locale locale = localeResolver.resolveLocale(request);
            String errorMessage = errors.getAllErrors().stream()
                    .map(oe -> messageSource.getMessage(oe, locale))
                    .reduce("errors:\n", (accu, oe) -> accu + oe + "\n");
            return ResponseEntity.badRequest().body(errorMessage);
        }

        mangaShop = mangaShopService.addMangaShop(mangaShop);
        log.info("new mangaShop added {}", mangaShop);
        return ResponseEntity.status(HttpStatus.CREATED).body(mangaShop);
    }
}
