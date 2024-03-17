package com.addario.webcrawlerapi.service;

import com.addario.webcrawlerapi.exceptions.URLException;
import com.addario.webcrawlerapi.model.Item;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Service
public class ItemScrapper {
    public static void main(String[] args) {
        String url = "https://www.posthaus.com.br/plus-size-feminino";

        try {
            Document document = Jsoup.connect(url).get();
            Elements elements = document.select("div.sc-aewfc.bQroGA");

            List<Item> itens = elements.stream().map(element -> {
                String productName = element.select("h4#id-produto-listagem-desktop").text();
                String productPrice = element.select("label.sc-jAaTju.irGtUG").text();

                BigDecimal price = BigDecimal.ZERO;
                if (!productPrice.isEmpty()) {
                    String priceString = productPrice.replaceAll("[^\\d.]", "");
                    price = new BigDecimal(priceString);

                }

                return Item.builder().name(productName).price(price).build();

            }).toList();

            System.out.println(itens.size());
        } catch (IOException e) {
            throw new URLException(format("An error occurred while trying to access the URL %s ", url), e);
        }
    }
}
