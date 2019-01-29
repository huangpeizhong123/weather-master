package amap.com.amapandgoogle;

import java.io.Serializable;
import java.util.List;

/**
 * Description:
 * Author: 黄培忠
 * CreateDate: 19/1/29 15:55
 * UpdateUser:
 * UpdateDate: 19/1/29 15:55
 * UpdateRemark:
 */
public class WeatherBean implements Serializable {
    /**
     * area_metadata : [{"name":"Ang Mo Kio","label_location":{"latitude":1.375,"longitude":103.839}},{"name":"Bedok","label_location":{"latitude":1.321,"longitude":103.924}},{"name":"Bishan","label_location":{"latitude":1.350772,"longitude":103.839}},{"name":"Boon Lay","label_location":{"latitude":1.304,"longitude":103.701}},{"name":"Bukit Batok","label_location":{"latitude":1.353,"longitude":103.754}},{"name":"Bukit Merah","label_location":{"latitude":1.277,"longitude":103.819}},{"name":"Bukit Panjang","label_location":{"latitude":1.362,"longitude":103.77195}},{"name":"Bukit Timah","label_location":{"latitude":1.325,"longitude":103.791}},{"name":"Central Water Catchment","label_location":{"latitude":1.38,"longitude":103.805}},{"name":"Changi","label_location":{"latitude":1.357,"longitude":103.987}},{"name":"Choa Chu Kang","label_location":{"latitude":1.377,"longitude":103.745}},{"name":"Clementi","label_location":{"latitude":1.315,"longitude":103.76}},{"name":"City","label_location":{"latitude":1.292,"longitude":103.844}},{"name":"Geylang","label_location":{"latitude":1.318,"longitude":103.884}},{"name":"Hougang","label_location":{"latitude":1.361218,"longitude":103.886}},{"name":"Jalan Bahar","label_location":{"latitude":1.347,"longitude":103.67}},{"name":"Jurong East","label_location":{"latitude":1.326,"longitude":103.737}},{"name":"Jurong Island","label_location":{"latitude":1.266,"longitude":103.699}},{"name":"Jurong West","label_location":{"latitude":1.34039,"longitude":103.705}},{"name":"Kallang","label_location":{"latitude":1.312,"longitude":103.862}},{"name":"Lim Chu Kang","label_location":{"latitude":1.423,"longitude":103.717332}},{"name":"Mandai","label_location":{"latitude":1.419,"longitude":103.812}},{"name":"Marine Parade","label_location":{"latitude":1.297,"longitude":103.891}},{"name":"Novena","label_location":{"latitude":1.327,"longitude":103.826}},{"name":"Pasir Ris","label_location":{"latitude":1.37,"longitude":103.948}},{"name":"Paya Lebar","label_location":{"latitude":1.358,"longitude":103.914}},{"name":"Pioneer","label_location":{"latitude":1.315,"longitude":103.675}},{"name":"Pulau Tekong","label_location":{"latitude":1.403,"longitude":104.053}},{"name":"Pulau Ubin","label_location":{"latitude":1.404,"longitude":103.96}},{"name":"Punggol","label_location":{"latitude":1.401,"longitude":103.904}},{"name":"Queenstown","label_location":{"latitude":1.291,"longitude":103.78576}},{"name":"Seletar","label_location":{"latitude":1.404,"longitude":103.869}},{"name":"Sembawang","label_location":{"latitude":1.445,"longitude":103.818495}},{"name":"Sengkang","label_location":{"latitude":1.384,"longitude":103.891443}},{"name":"Sentosa","label_location":{"latitude":1.243,"longitude":103.832}},{"name":"Serangoon","label_location":{"latitude":1.357,"longitude":103.865}},{"name":"Southern Islands","label_location":{"latitude":1.208,"longitude":103.842}},{"name":"Sungei Kadut","label_location":{"latitude":1.413,"longitude":103.756}},{"name":"Tampines","label_location":{"latitude":1.345,"longitude":103.944}},{"name":"Tanglin","label_location":{"latitude":1.308,"longitude":103.813}},{"name":"Tengah","label_location":{"latitude":1.374,"longitude":103.715}},{"name":"Toa Payoh","label_location":{"latitude":1.334304,"longitude":103.856327}},{"name":"Tuas","label_location":{"latitude":1.294947,"longitude":103.635}},{"name":"Western Islands","label_location":{"latitude":1.205926,"longitude":103.746}},{"name":"Western Water Catchment","label_location":{"latitude":1.405,"longitude":103.689}},{"name":"Woodlands","label_location":{"latitude":1.432,"longitude":103.786528}},{"name":"Yishun","label_location":{"latitude":1.418,"longitude":103.839}}]
     * items : [{"update_timestamp":"2019-01-27T10:08:52+08:00","timestamp":"2019-01-27T10:00:00+08:00","valid_period":{"start":"2019-01-27T10:00:00+08:00","end":"2019-01-27T12:00:00+08:00"},"forecasts":[{"area":"Ang Mo Kio","forecast":"Windy"},{"area":"Bedok","forecast":"Windy"},{"area":"Bishan","forecast":"Windy"},{"area":"Boon Lay","forecast":"Windy"},{"area":"Bukit Batok","forecast":"Windy"},{"area":"Bukit Merah","forecast":"Windy"},{"area":"Bukit Panjang","forecast":"Windy"},{"area":"Bukit Timah","forecast":"Windy"},{"area":"Central Water Catchment","forecast":"Windy"},{"area":"Changi","forecast":"Windy"},{"area":"Choa Chu Kang","forecast":"Windy"},{"area":"Clementi","forecast":"Windy"},{"area":"City","forecast":"Windy"},{"area":"Geylang","forecast":"Windy"},{"area":"Hougang","forecast":"Windy"},{"area":"Jalan Bahar","forecast":"Windy"},{"area":"Jurong East","forecast":"Windy"},{"area":"Jurong Island","forecast":"Windy"},{"area":"Jurong West","forecast":"Windy"},{"area":"Kallang","forecast":"Windy"},{"area":"Lim Chu Kang","forecast":"Windy"},{"area":"Mandai","forecast":"Windy"},{"area":"Marine Parade","forecast":"Windy"},{"area":"Novena","forecast":"Windy"},{"area":"Pasir Ris","forecast":"Windy"},{"area":"Paya Lebar","forecast":"Windy"},{"area":"Pioneer","forecast":"Windy"},{"area":"Pulau Tekong","forecast":"Windy"},{"area":"Pulau Ubin","forecast":"Windy"},{"area":"Punggol","forecast":"Windy"},{"area":"Queenstown","forecast":"Windy"},{"area":"Seletar","forecast":"Windy"},{"area":"Sembawang","forecast":"Windy"},{"area":"Sengkang","forecast":"Windy"},{"area":"Sentosa","forecast":"Windy"},{"area":"Serangoon","forecast":"Windy"},{"area":"Southern Islands","forecast":"Windy"},{"area":"Sungei Kadut","forecast":"Windy"},{"area":"Tampines","forecast":"Windy"},{"area":"Tanglin","forecast":"Windy"},{"area":"Tengah","forecast":"Windy"},{"area":"Toa Payoh","forecast":"Windy"},{"area":"Tuas","forecast":"Windy"},{"area":"Western Islands","forecast":"Windy"},{"area":"Western Water Catchment","forecast":"Windy"},{"area":"Woodlands","forecast":"Windy"},{"area":"Yishun","forecast":"Windy"}]}]
     * api_info : {"status":"healthy"}
     */

    private ApiInfoBean api_info;
    private List<AreaMetadataBean> area_metadata;
    private List<ItemsBean> items;

    public ApiInfoBean getApi_info() {
        return api_info;
    }

    public void setApi_info(ApiInfoBean api_info) {
        this.api_info = api_info;
    }

    public List<AreaMetadataBean> getArea_metadata() {
        return area_metadata;
    }

    public void setArea_metadata(List<AreaMetadataBean> area_metadata) {
        this.area_metadata = area_metadata;
    }

    public List<ItemsBean> getItems() {
        return items;
    }

    public void setItems(List<ItemsBean> items) {
        this.items = items;
    }

    public static class ApiInfoBean {
        /**
         * status : healthy
         */

        private String status;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }

    public static class AreaMetadataBean {
        /**
         * name : Ang Mo Kio
         * label_location : {"latitude":1.375,"longitude":103.839}
         */

        private String name;
        private LabelLocationBean label_location;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public LabelLocationBean getLabel_location() {
            return label_location;
        }

        public void setLabel_location(LabelLocationBean label_location) {
            this.label_location = label_location;
        }

        public static class LabelLocationBean {
            /**
             * latitude : 1.375
             * longitude : 103.839
             */

            private double latitude;
            private double longitude;

            public double getLatitude() {
                return latitude;
            }

            public void setLatitude(double latitude) {
                this.latitude = latitude;
            }

            public double getLongitude() {
                return longitude;
            }

            public void setLongitude(double longitude) {
                this.longitude = longitude;
            }
        }
    }

    public static class ItemsBean {
        /**
         * update_timestamp : 2019-01-27T10:08:52+08:00
         * timestamp : 2019-01-27T10:00:00+08:00
         * valid_period : {"start":"2019-01-27T10:00:00+08:00","end":"2019-01-27T12:00:00+08:00"}
         * forecasts : [{"area":"Ang Mo Kio","forecast":"Windy"},{"area":"Bedok","forecast":"Windy"},{"area":"Bishan","forecast":"Windy"},{"area":"Boon Lay","forecast":"Windy"},{"area":"Bukit Batok","forecast":"Windy"},{"area":"Bukit Merah","forecast":"Windy"},{"area":"Bukit Panjang","forecast":"Windy"},{"area":"Bukit Timah","forecast":"Windy"},{"area":"Central Water Catchment","forecast":"Windy"},{"area":"Changi","forecast":"Windy"},{"area":"Choa Chu Kang","forecast":"Windy"},{"area":"Clementi","forecast":"Windy"},{"area":"City","forecast":"Windy"},{"area":"Geylang","forecast":"Windy"},{"area":"Hougang","forecast":"Windy"},{"area":"Jalan Bahar","forecast":"Windy"},{"area":"Jurong East","forecast":"Windy"},{"area":"Jurong Island","forecast":"Windy"},{"area":"Jurong West","forecast":"Windy"},{"area":"Kallang","forecast":"Windy"},{"area":"Lim Chu Kang","forecast":"Windy"},{"area":"Mandai","forecast":"Windy"},{"area":"Marine Parade","forecast":"Windy"},{"area":"Novena","forecast":"Windy"},{"area":"Pasir Ris","forecast":"Windy"},{"area":"Paya Lebar","forecast":"Windy"},{"area":"Pioneer","forecast":"Windy"},{"area":"Pulau Tekong","forecast":"Windy"},{"area":"Pulau Ubin","forecast":"Windy"},{"area":"Punggol","forecast":"Windy"},{"area":"Queenstown","forecast":"Windy"},{"area":"Seletar","forecast":"Windy"},{"area":"Sembawang","forecast":"Windy"},{"area":"Sengkang","forecast":"Windy"},{"area":"Sentosa","forecast":"Windy"},{"area":"Serangoon","forecast":"Windy"},{"area":"Southern Islands","forecast":"Windy"},{"area":"Sungei Kadut","forecast":"Windy"},{"area":"Tampines","forecast":"Windy"},{"area":"Tanglin","forecast":"Windy"},{"area":"Tengah","forecast":"Windy"},{"area":"Toa Payoh","forecast":"Windy"},{"area":"Tuas","forecast":"Windy"},{"area":"Western Islands","forecast":"Windy"},{"area":"Western Water Catchment","forecast":"Windy"},{"area":"Woodlands","forecast":"Windy"},{"area":"Yishun","forecast":"Windy"}]
         */

        private String update_timestamp;
        private String timestamp;
        private ValidPeriodBean valid_period;
        private List<ForecastsBean> forecasts;

        public String getUpdate_timestamp() {
            return update_timestamp;
        }

        public void setUpdate_timestamp(String update_timestamp) {
            this.update_timestamp = update_timestamp;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }

        public ValidPeriodBean getValid_period() {
            return valid_period;
        }

        public void setValid_period(ValidPeriodBean valid_period) {
            this.valid_period = valid_period;
        }

        public List<ForecastsBean> getForecasts() {
            return forecasts;
        }

        public void setForecasts(List<ForecastsBean> forecasts) {
            this.forecasts = forecasts;
        }

        public static class ValidPeriodBean {
            /**
             * start : 2019-01-27T10:00:00+08:00
             * end : 2019-01-27T12:00:00+08:00
             */

            private String start;
            private String end;

            public String getStart() {
                return start;
            }

            public void setStart(String start) {
                this.start = start;
            }

            public String getEnd() {
                return end;
            }

            public void setEnd(String end) {
                this.end = end;
            }
        }

        public static class ForecastsBean {
            /**
             * area : Ang Mo Kio
             * forecast : Windy
             */

            private String area;
            private String forecast;

            public String getArea() {
                return area;
            }

            public void setArea(String area) {
                this.area = area;
            }

            public String getForecast() {
                return forecast;
            }

            public void setForecast(String forecast) {
                this.forecast = forecast;
            }
        }
    }
}
