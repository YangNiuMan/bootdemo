SPI机制：
    其实就是定义一个接口，由外部定义实现方式


使用：
    1、第一个工程定义接口，方法
    2、第二个工程引入包，然后构建实现类，在resources目录下建立一
    个META-INF/services目录,然后建一个实现接口的全限定名的文件，
    最后将实现类的全限定类名写入配置文件中
    ServiceLoader<MyServletContainerInitializer> loader = ServiceLoader.load(MyServletContainerInitializer.class);
            Iterator<MyServletContainerInitializer> iterator = loader.iterator();
            while (iterator.hasNext()){
                MyServletContainerInitializer next = iterator.next();
                next.onStartup();
    }