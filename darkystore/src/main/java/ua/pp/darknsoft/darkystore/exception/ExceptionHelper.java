package ua.pp.darknsoft.darkystore.exception;

import jakarta.validation.constraints.NotNull;
import org.springframework.core.MethodParameter;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.lang.reflect.Method;
import java.util.Map;

public class ExceptionHelper {
    public ExceptionHelper() {
        throw new AssertionError("Utility class cannot be instantiated");
    }

    public static MethodArgumentNotValidException createValidationException(@NotNull Object object, @NotNull Map<String, String> errors) {

        BeanPropertyBindingResult bindingResult = new BeanPropertyBindingResult(object, object.getClass().getSimpleName());

        errors.forEach((k, v) -> {
            bindingResult.addError(new FieldError(object.getClass().getSimpleName(), k, v));
        });
        MethodParameter methodParameter = getFakeMethodParameter();
        return new MethodArgumentNotValidException(methodParameter, bindingResult);
    }

    // Допоміжний метод для отримання MethodParameter (вимагається конструктором Spring)
    private static MethodParameter getFakeMethodParameter() {
        try {
            Method method = ExceptionHelper.class.getDeclaredMethod("fakeMethod", String.class);
            return new MethodParameter(method, 0); // 0 — індекс параметра
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    // Фейковий метод суто для того, щоб Spring отримав метадані параметра
    private static void fakeMethod(String dummy) {
    }
}
