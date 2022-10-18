package ua.goit.petstore.models;

import java.util.Arrays;
import java.util.List;

public class Pet {
    private Long id;
    private Category category;
    private String name;
    private String[] photoUrls;
    private List<Tag> tags;
    private PetStatus status;

    public Pet() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getPhotoUrls() {
        return photoUrls;
    }

    public void setPhotoUrls(String[] photoUrls) {
        this.photoUrls = photoUrls;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public PetStatus getStatus() {
        return status;
    }

    public void setStatus(PetStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Pet{");
        sb.append("id=").append(id);
        sb.append(", category=").append(category);
        sb.append(", name='").append(name).append('\'');
        sb.append(", photoUrls=").append(Arrays.toString(photoUrls));
        sb.append(", tags=").append(tags);
        sb.append(", status=").append(status);
        sb.append('}');
        return sb.toString();
    }
}
