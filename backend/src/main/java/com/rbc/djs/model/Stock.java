package com.rbc.djs.model;

import java.util.Objects;

import com.opencsv.bean.CsvBindByName;

import lombok.Data;
import lombok.NonNull;

/**
 * Stock
 * 
 * quarter,stock,date,open,high,low,close,volume,percent_change_price,percent_change_volume_over_last_wk,previous_weeks_volume,next_weeks_open,next_weeks_close,percent_change_next_weeks_price,days_to_next_dividend,percent_return_next_dividend
 */
@Data
public class Stock {

  @CsvBindByName()
  private String quarter = null;

  @NonNull
  @CsvBindByName()
  private String stock = null;

  @CsvBindByName()
  private String date = null;

  @CsvBindByName()
  private String open = null;

  @CsvBindByName()
  private String high = null;

  @CsvBindByName()
  private String low = null;

  @CsvBindByName()
  private String close = null;

  @CsvBindByName()
  private String volume = null;

  @CsvBindByName(column = "percent_change_price")
  private String percentChangePrice = null;

  @CsvBindByName(column = "percent_change_volume_over_last_wk")
  private String percentChangeVolumeOverLastWk = null;

  @CsvBindByName(column = "previous_weeks_volume")
  private String previousWeeksVolume = null;

  @CsvBindByName(column = "next_weeks_open")
  private String nextWeeksOpen = null;

  @CsvBindByName(column = "next_weeks_close")
  private String nextWeeksClose = null;

  @CsvBindByName(column = "percent_change_next_weeks_price")
  private String percentChangeNextWeeksPrice = null;

  @CsvBindByName(column = "days_to_next_dividend")
  private String daysToNextDividend = null;

  @CsvBindByName(column = "percent_return_next_dividend")
  private String percentReturnNextDividend = null;

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Stock stock = (Stock) o;
    return Objects.equals(this.quarter, stock.quarter) &&
        Objects.equals(this.stock, stock.stock) &&
        Objects.equals(this.date, stock.date) &&
        Objects.equals(this.open, stock.open) &&
        Objects.equals(this.high, stock.high) &&
        Objects.equals(this.low, stock.low) &&
        Objects.equals(this.close, stock.close) &&
        Objects.equals(this.volume, stock.volume) &&
        Objects.equals(this.percentChangePrice, stock.percentChangePrice) &&
        Objects.equals(this.percentChangeVolumeOverLastWk, stock.percentChangeVolumeOverLastWk) &&
        Objects.equals(this.previousWeeksVolume, stock.previousWeeksVolume) &&
        Objects.equals(this.nextWeeksOpen, stock.nextWeeksOpen) &&
        Objects.equals(this.nextWeeksClose, stock.nextWeeksClose) &&
        Objects.equals(this.percentChangeNextWeeksPrice, stock.percentChangeNextWeeksPrice) &&
        Objects.equals(this.daysToNextDividend, stock.daysToNextDividend) &&
        Objects.equals(this.percentReturnNextDividend, stock.percentReturnNextDividend);
  }

  @Override
  public int hashCode() {
    return Objects.hash(quarter, stock, date, open, high, low, close, volume, percentChangePrice,
        percentChangeVolumeOverLastWk, previousWeeksVolume, nextWeeksOpen, nextWeeksClose, percentChangeNextWeeksPrice,
        daysToNextDividend, percentReturnNextDividend);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Stock {\n");

    sb.append("    quarter: ").append(toIndentedString(quarter)).append("\n");
    sb.append("    stock: ").append(toIndentedString(stock)).append("\n");
    sb.append("    date: ").append(toIndentedString(date)).append("\n");
    sb.append("    open: ").append(toIndentedString(open)).append("\n");
    sb.append("    high: ").append(toIndentedString(high)).append("\n");
    sb.append("    low: ").append(toIndentedString(low)).append("\n");
    sb.append("    close: ").append(toIndentedString(close)).append("\n");
    sb.append("    volume: ").append(toIndentedString(volume)).append("\n");
    sb.append("    percentChangePrice: ").append(toIndentedString(percentChangePrice)).append("\n");
    sb.append("    percentChangeVolumeOverLastWk: ").append(toIndentedString(percentChangeVolumeOverLastWk))
        .append("\n");
    sb.append("    previousWeeksVolume: ").append(toIndentedString(previousWeeksVolume)).append("\n");
    sb.append("    nextWeeksOpen: ").append(toIndentedString(nextWeeksOpen)).append("\n");
    sb.append("    nextWeeksClose: ").append(toIndentedString(nextWeeksClose)).append("\n");
    sb.append("    percentChangeNextWeeksPrice: ").append(toIndentedString(percentChangeNextWeeksPrice)).append("\n");
    sb.append("    daysToNextDividend: ").append(toIndentedString(daysToNextDividend)).append("\n");
    sb.append("    percentReturnNextDividend: ").append(toIndentedString(percentReturnNextDividend)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
