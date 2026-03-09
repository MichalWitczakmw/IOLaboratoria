package vod.web.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import vod.model.MangaShop;
import vod.service.MangaShopService;

@Component
@RequiredArgsConstructor
public class MangaShopValidator implements Validator {

    private final MangaShopService mangaShopService;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(MangaShop.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        MangaShop validatedMangaShop = (MangaShop) target;

        boolean duplicated = mangaShopService.getAllMangaShops().stream()
                .anyMatch(mangaShop -> mangaShop.getName()
                        .equalsIgnoreCase(validatedMangaShop.getName()));

        if (duplicated) {
            errors.rejectValue("name", "mangaShop.name.duplicated");
        }
    }
}
