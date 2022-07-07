package app.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.gridfs.GridFsCriteria
import org.springframework.data.mongodb.gridfs.GridFsResource
import org.springframework.data.mongodb.gridfs.GridFsTemplate
import org.springframework.http.MediaType
import org.springframework.stereotype.Service


@Service
class FileService {


    private final GridFsTemplate gridFsTemplate


    @Autowired
    FileService(GridFsTemplate gridFsTemplate) {

        this.gridFsTemplate = gridFsTemplate
    }


    void save(InputStream file, String filename, String mediaType) {

        this.gridFsTemplate.delete(Query.query(GridFsCriteria.whereFilename().is(filename)))
        this.gridFsTemplate.store(file, filename, mediaType)
    }

    void saveImage(InputStream file, String filename) {
        this.save(file, filename, MediaType.IMAGE_JPEG_VALUE)
    }

    GridFsResource[] findAll() {
        return this.gridFsTemplate.getResources("*")
    }

    GridFsResource findOne(String filename) {
        return this.gridFsTemplate.getResource(filename)
    }

    void deleteAll() {
        this.findAll().each { this.deleteOne(it.getFilename())}
    }

    void deleteOne(String filename) {
        this.gridFsTemplate.delete(Query.query(GridFsCriteria.whereFilename().is(filename)))
    }


}
