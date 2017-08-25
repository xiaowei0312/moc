package com.sram;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class JsonDateSerializer extends JsonSerializer<Date> {
  private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
 
  @Override
  public void serialize(Date arg0, JsonGenerator arg1, SerializerProvider arg2)
          throws IOException, JsonProcessingException {
      // TODO Auto-generated method stub
      String formattedDate = dateFormat.format(arg0);
      arg1.writeString(formattedDate);
  }
}
