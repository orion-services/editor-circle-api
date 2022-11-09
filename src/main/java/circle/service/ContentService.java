package circle.service;

import circle.model.Content;
import circle.repository.ContentRepository;
import org.springframework.stereotype.Service;

import javax.ws.rs.FormParam;

@Service
public class ContentService {
    private final ContentRepository contentRepository;

    public ContentService(ContentRepository contentRepository) {
        this.contentRepository = contentRepository;
    }

    public Content contentToCreate(@FormParam("dataContent") final String dataContent) {
        final Content createContent = new Content();
        createContent.setDataContent(dataContent);
        contentRepository.save(createContent);
        return createContent;
    }
}
