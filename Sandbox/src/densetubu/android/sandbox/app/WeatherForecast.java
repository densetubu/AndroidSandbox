package densetubu.android.sandbox.app;

class WeatherForecast {

    static class Builder {
        private Weather today;
        private Weather tomorrow;
        private Weather dayAfterTomorrow;

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

        Builder setDayAfterTomorrow(Weather weather) {
            this.dayAfterTomorrow = weather;
            return this;
        }

    }

    private final Weather today;
    private final Weather tomorrow;
    private final Weather dayAfterTomorrow;

    WeatherForecast(Builder builder) {
        this.today = builder.today;
        this.tomorrow = builder.tomorrow;
        this.dayAfterTomorrow = builder.dayAfterTomorrow;
    }

    Weather getToday() {
        return today;
    }

    Weather getTomorrow() {
        return tomorrow;
    }

    Weather getDayAfterTomorrow() {
        return dayAfterTomorrow;
    }
}
