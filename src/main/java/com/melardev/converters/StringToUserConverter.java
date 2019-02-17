package com.melardev.converters;

import com.melardev.models.User;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringToUserConverter extends AbstractHttpMessageConverter<User> {


    @Override
    protected boolean supports(Class<?> clazz) {
        //return User.class == clazz;
        if (User.class == clazz)
            return true;
        return false;
    }


    @Override
    protected User readInternal(Class<? extends User> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        String requestStr = IOUtils.toString(inputMessage.getBody(), "UTF-8");
        Pattern pattern = Pattern.compile("(&|\\?)firstname=(\\s+).*(&|\\?)lastname=(\\s+).*");
        Matcher matcher = pattern.matcher(requestStr);
        if (matcher.matches()) {
            User user = new User();
            user.setFirstName(matcher.group(0));
            user.setLastName(matcher.group(1));
            return user;
        }
        throw new HttpMessageNotReadableException("Could not read input");
    }

    @Override
    protected void writeInternal(User user, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        outputMessage.getBody().write((user.getFirstName() + user.getLastName()).getBytes());
    }
}
