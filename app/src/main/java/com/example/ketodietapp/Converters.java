package com.example.ketodietapp;

import androidx.room.TypeConverter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Converters {

    @TypeConverter
    public static String doubleListToString(List<Double> doubles) {
        return convertListToString(doubles);
    }

    @TypeConverter
    public static List<Double> doubleListFromString(String value) {
        return convertToList(value, Double::valueOf);
    }

    @TypeConverter
    public static List<String> stringListFromString(String value) {
        return convertToList(value, s -> s);
    }

    @TypeConverter
    public static String stringListToString(List<String> strings) {
        return convertListToString(strings);
    }

    private static <T> List<T> convertToList(String value, ValueConverter<T> converter) {
        return Arrays.stream(value.split(","))
                .map(converter::convert)
                .collect(Collectors.toList());
    }

    private static <T> String convertListToString(List<T> list) {
        return list.stream().map(Object::toString).reduce((s1, s2) -> s1 + "," + s2).orElse("");
    }


    private interface ValueConverter<T> {
        T convert(String value);
    }
}