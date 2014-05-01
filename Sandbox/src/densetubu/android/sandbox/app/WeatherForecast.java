package densetubu.android.sandbox.app;

class WeatherForecast {

    static class Builder {
        private Weather today;
        private Weather tomorrow;

        WeatherForecast build() {
            return new WeatherForecast(this);
        }

        Builder setToday(Weather weather) {
            this.today = weather;
            return this;
        }

        Builder setTomorrow(Weather weather) {
            this.tomorrow = weather;
            return this;
        }

    }

    private final Weather today;
    private final Weather tomorrow;

    WeatherForecast(Builder builder) {
        this.today = builder.today;
        this.tomorrow = builder.tomorrow;
    }

    Weather getToday() {
        return today;
    }

    Weather getTomorrow() {
        return tomorrow;
    }
}
