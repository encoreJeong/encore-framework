package com.encorejeong.encoreframework.web.handler.mapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PathPattern {

    private final Pattern regex;
    private final List<String> variableNames;

    public PathPattern(String path) {

        String[] tokens = path.split("/");

        StringBuilder regexBuilder = new StringBuilder();
        List<String> varNames = new ArrayList<>();

        Arrays.stream(tokens).forEach(token -> {

            if(token.isBlank())
                return;

            regexBuilder.append("/");
            if(token.startsWith("{") && token.endsWith("}")) {
                varNames.add(token.substring(1, token.length() - 1));
                regexBuilder.append("([^/]+)");
            } else {
                regexBuilder.append(Pattern.quote(token));
            }

        });

        this.regex = Pattern.compile(regexBuilder.toString());
        this.variableNames = varNames;
    }

    Matcher matcher(String path) {
        return regex.matcher(path);
    }

    public List<String> getVariableNames() {
        return variableNames;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PathPattern other)) return false;

        return Objects.equals(regex.pattern(), other.regex.pattern());
    }

    @Override
    public int hashCode() {
        return Objects.hash(regex.pattern());
    }

    @Override
    public String toString() {
        return regex.pattern();
    }
}
