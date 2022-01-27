package com.rukko.parkour.backend;

 /**
  * This file is part of a ViiictorXD project
  *
  * Copyright (c) ViiictorXD
  * https://github.com/viiictorxd
  **/
public class Utils {

    public static Integer parseInt(String subject) {
        try {
            return Integer.parseInt(subject);
        } catch (NumberFormatException exception) {
            return null;
        }
    }
}
