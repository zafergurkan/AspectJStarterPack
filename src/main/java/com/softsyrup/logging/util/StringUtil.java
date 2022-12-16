package com.softsyrup.logging.util;

import com.softsyrup.logging.aspect.LogMethod;
import org.springframework.stereotype.Component;

@Component
public class StringUtil {
    @LogMethod
    public String concatNameSurname(String name, String surname)
    {
        return name + surname;
    }
}
