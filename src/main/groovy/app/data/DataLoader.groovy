package app.data

import app.service.FileService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service

@Service
@Profile("!production")
class DataLoader {

    @Autowired
    FileService fileService

    @Autowired
    ApplicationContext applicationContext


    //@PostConstruct
    void init() {

        this.fileService.deleteAll()


        InputStream image1 = loadImage("cat.jpg")
        InputStream image2 = loadImage("caterpillar.jpg")

        this.fileService.saveImage(image1, "cat.jpg")
        this.fileService.saveImage(image2, "caterpillar.jpg")
    }



    private InputStream loadImage(String filename) {
        return applicationContext.getResource("classpath:" + filename).getInputStream()
    }



}
