package com.rbc.djs.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.opencsv.bean.CsvToBeanBuilder;
import com.rbc.djs.exception.StockDataNotFoundException;
import com.rbc.djs.model.Stock;
import com.rbc.djs.repository.StockDataRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api")
@Slf4j
public class StockDataController {

        @Autowired
        private StockDataRepository stockRepository;

        @Operation(summary = "retrive all stock data records", description = "get all stock data records", tags = {
                        "stock-data" })
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "get All stock data", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Stock.class))),
        })
        @GetMapping("/stock-data")
        public Flux<Stock> getAllStockData() {
                Flux<Stock> stockData = stockRepository.findAll();
                return stockData;
        }

        @Operation(summary = "Add a new stock data record to the store", description = "Add a new stock data to the store", tags = {
                        "stock-data" })
        @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "create stock data"),
                        @ApiResponse(responseCode = "201", description = "Successful operation", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Stock.class))),
                        @ApiResponse(responseCode = "405", description = "Invalid input") })
        @PostMapping("/stock-data")
        public Mono<Stock> createStockData(@Valid @RequestBody Stock stock) {
                return stockRepository.save(stock);
        }

        @Operation(summary = "Find stock data by symbol", description = "Returns a single stock record by stock symbol", tags = {
                        "stock-data" })
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "successful operation get Stock Data By Stock Symbol", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Stock.class))),
                        @ApiResponse(responseCode = "400", description = "Invalid stock supplied"),
                        @ApiResponse(responseCode = "404", description = "stock data not found") })
        @GetMapping("/stock-data/{stock}")
        public Flux<Stock> getStockDataByStock(@PathVariable(value = "stock") String stock) {
                return stockRepository.findByStock(stock)
                                .switchIfEmpty(Mono.error(new StockDataNotFoundException(stock)));
        }

        @Operation(summary = "uploads csv file", description = "uploads an uci stock data csv file", tags = {
                        "stock-data" })
        @PostMapping(path = "/stock-data/bulk-insert", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
        @ResponseStatus(value = HttpStatus.OK)
        @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "create stock data") })
        public ResponseEntity<String> upload(@RequestPart("files") FilePart filePart) {

                String filename = filePart.filename();

                filePart.content().map(DataBuffer::asInputStream).map(is -> {
                        try {
                                BufferedReader br = new BufferedReader(
                                                new InputStreamReader(is, StandardCharsets.UTF_8));
                                List<Stock> stockData = new CsvToBeanBuilder<Stock>(br).withType(Stock.class).build()
                                                .parse();

                                return stockRepository.saveAll(stockData).subscribe();

                        } catch (Exception e) {
                                return Mono.empty();
                        }

                }).subscribe();

                return ResponseEntity.ok("Uploadd file success!" + filename);

        }

}
