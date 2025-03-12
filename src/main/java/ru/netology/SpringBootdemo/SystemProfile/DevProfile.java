package ru.netology.SpringBootdemo.SystemProfile;

public class DevProfile implements SystemProfile{

    @Override
    public String getProfile() {
        return "Current profile is dev";
    }
}
