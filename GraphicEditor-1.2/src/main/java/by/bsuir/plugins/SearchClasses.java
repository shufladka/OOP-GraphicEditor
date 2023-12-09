package by.bsuir.plugins;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

// class that searches for shape classes in a given package
public class SearchClasses {
    public static List<Class> getClassesFromPackage(String packageName, Class basicClass) throws IOException, ClassNotFoundException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        assert classLoader != null;
        String path = packageName.replace('.', '/');
        Enumeration<URL> resources = classLoader.getResources(path);
        List<File> dirs = new ArrayList<>();
        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            dirs.add(new File(resource.getFile()));
        }

        ArrayList<Class> classes = new ArrayList<>();
        for (File dir : dirs) {
            classes.addAll(findClasses(dir, packageName, basicClass));
        }

        return classes;
    }

    private static List<Class> findClasses(File directory, String packageName, Class isSubclassOf) throws ClassNotFoundException {
        List<Class> classes = new ArrayList<>();
        if (!directory.exists()) {
            return classes;
        }
        File[] files = directory.listFiles();
        for (File file : files) {
            if (!file.isDirectory() && file.getName().endsWith(".class")) {
                Class clazz = Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6));
                if (isSubclassOf.isAssignableFrom(clazz) && !isSubclassOf.equals(clazz)) {
                    classes.add(clazz);
                }
            }
        }
        return classes;
    }
}
