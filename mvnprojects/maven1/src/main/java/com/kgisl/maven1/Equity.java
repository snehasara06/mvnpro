package com.kgisl.maven1;
import com.opencsv.bean.CsvBindByPosition;

public class Equity {
    @CsvBindByPosition(position = 0)
    public String symbol;

    @CsvBindByPosition(position = 1)
    public String company;

    @CsvBindByPosition(position = 2)
    public String series;

    @CsvBindByPosition(position = 3)
    public String listingdate;

    @CsvBindByPosition(position = 4)
    public String paid;

    @CsvBindByPosition(position = 5)
    public String mktlot;

    @CsvBindByPosition(position = 6)
    public String isin;

    @CsvBindByPosition(position = 7)
    public String facevalue;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getListingdate() {
        return listingdate;
    }

    public void setListingdate(String listingdate) {
        this.listingdate = listingdate;
    }

    public String getPaid() {
        return paid;
    }

    public void setPaid(String paid) {
        this.paid = paid;
    }

    public String getMktlot() {
        return mktlot;
    }

    public void setMktlot(String mktlot) {
        this.mktlot = mktlot;
    }

    public String getIsin() {
        return isin;
    }

    public void setIsin(String isin) {
        this.isin = isin;
    }

    public String getFacevalue() {
        return facevalue;
    }

    public void setFacevalue(String facevalue) {
        this.facevalue = facevalue;
    }

    @Override
    public String toString() {
        return "Equity [symbol=" + symbol + ", company=" + company + ", series=" + series + ", listingdate="
                + listingdate + ", paid=" + paid + ", mktlot=" + mktlot + ", isin=" + isin + ", facevalue=" + facevalue
                + "]";
    }

}
