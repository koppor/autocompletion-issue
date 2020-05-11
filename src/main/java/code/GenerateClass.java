package code;

import java.nio.file.Path;
import java.util.Iterator;
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
        TypeSpec.Builder constantsClass = TypeSpec.classBuilder("Constants").addModifiers(Modifier.PUBLIC,
                Modifier.FINAL);

        List<Character> characters = IntStream
                .concat(IntStream.concat(IntStream.range('0', '9'), IntStream.range('a', 'z')),
                        IntStream.range('A', 'Z'))
                .mapToObj(i -> (char) i).collect(Collectors.toList());

        int count = 0;

        Iterator<Character> firstIterator = characters.iterator();

        while ((count < 12_000) && firstIterator.hasNext()) {
            Character firstLetter = firstIterator.next();
            characters.forEach(secondLetter -> characters.forEach(thirdLetter -> {
                String fieldName = "Code_" + firstLetter + secondLetter + thirdLetter;
                constantsClass.addField(
                        FieldSpec.builder(String.class, fieldName, Modifier.PUBLIC, Modifier.STATIC, Modifier.FINAL)
                                .initializer("\"some-data\"").build());
            }));
            count += characters.size() * characters.size();
        }

        JavaFile.builder("code", constantsClass.build()).build().writeTo(Path.of("src", "gen", "java"));
    }
}
