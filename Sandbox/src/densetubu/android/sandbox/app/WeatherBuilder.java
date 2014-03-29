package densetubu.android.sandbox.app;

class WeatherBuilder {

    private String day;
    private String name;

    Weather build() {
        return new WeatherImpl(this);
    }

    //内部クラスは static にしないとメモリリークの原因になる
    private static class WeatherImpl implements Weather {

        private final String day;
        private final String name;

        private WeatherImpl(WeatherBuilder builder) {
            day = builder.day;
            name = builder.name;
        }

        @Override
        public String getDay() {
            return day;
        }

        @Override
        public String getName() {
            return name;
        }

        /**
         * データを表すクラスは {@link Object#toString} を Override しておくと何かと便利
         */
        @Override
        public String toString() {
            return day + " " + name;
        }
    }

    WeatherBuilder setDay(String day) {
        this.day = day;
        return this;
    }

    WeatherBuilder setName(String name) {
        this.name = name;
        return this;
    }
}
