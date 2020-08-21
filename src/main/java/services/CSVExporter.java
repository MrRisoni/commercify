package services;

import com.fasterxml.classmate.GenericType;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import dto.CsvRecord;
import dto.OrderCsvExportRow;

import javax.servlet.http.HttpServletResponse;

public class CSVExporter {

    private static HttpServletResponse response;


    public HttpServletResponse getResponse() {
        return response;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }


    public static StatefulBeanToCsv getWriter(CsvRecord r) {
        try {
            return new StatefulBeanToCsvBuilder<CsvRecord>(response.getWriter())
                    .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                    .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                    .withOrderedResults(false)
                    .build();

        }
        catch (Exception ex)
        {
            return null;
        }
    }
}
