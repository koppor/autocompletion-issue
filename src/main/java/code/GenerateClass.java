package code;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import javax.lang.model.element.Modifier;

import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;

public class GenerateClass {

    public static void main(String[] args) throws Exception {
        TypeSpec.Builder constantsClass = TypeSpec.classBuilder("Constants")
                                                  .addModifiers(Modifier.PUBLIC, Modifier.FINAL);

        List<Character> characters = IntStream.concat(IntStream.concat(
                IntStream.range('0', '9'),
                IntStream.range('a', 'z')),
                IntStream.range('A', 'Z'))
                                              .mapToObj(i -> (char) i)
                                              .collect(Collectors.toList());

        characters.forEach(firstLetter -> characters.forEach(secondLetter -> {
            String fieldName = "Code_" + firstLetter + secondLetter;
            constantsClass.addField(
                    FieldSpec.builder(String.class,
                            fieldName,
                            Modifier.PUBLIC, Modifier.STATIC, Modifier.FINAL)
                             .initializer("\"some-data\"")
                             .build());
        }));

        JavaFile.builder("code", constantsClass.build())
                .build()
                .writeTo(Path.of("src", "gen", "java"));
    }
}
