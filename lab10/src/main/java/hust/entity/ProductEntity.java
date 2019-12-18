package main.java.hust.entity;

import javax.persistence.*;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "product", schema = "emarket", catalog = "")
public class ProductEntity {
    private int productId;
    private String name;
    private BigDecimal price;
    private String description;
    private Timestamp lastUpdate;
    private int categoryId;
    private String image;
    private String thumbImage;
    private String descriptionDetail;

    public ProductEntity() {
    }

    public ProductEntity(String name, BigDecimal price, String description, Timestamp lastUpdate,
                         int categoryId, String image, String thumbImage, String descriptionDetail) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.lastUpdate = lastUpdate;
        this.categoryId = categoryId;
        this.image = image;
        this.thumbImage = thumbImage;
        this.descriptionDetail = descriptionDetail;
    }

    public ProductEntity(HttpServletRequest request) {
        name = request.getParameter("name");
        price = BigDecimal.valueOf(Double.parseDouble(request.getParameter("price")));
        String categoryName = request.getParameter("category");
        if (categoryName != null) {
            switch (categoryName) {
                case "Mac":
                    categoryId = 1;
                    break;
                case "iPad":
                    categoryId = 2;
                    break;
                case "iPhone":
                    categoryId = 3;
                    break;
                case "Accessories":
                    categoryId = 4;
                    break;
            }
        }
        image = request.getParameter("image");
        image = image == null ? (categoryId + ".jpg") : image;
        thumbImage = request.getParameter("thumbnailImage");
        thumbImage = thumbImage == null ? ("tn-" + categoryId + ".jpg") : thumbImage;
        description = request.getParameter("description");
        descriptionDetail = request.getParameter("descriptionDetail");
        lastUpdate = new Timestamp(System.currentTimeMillis());
    }

    @Id
    @Column(name = "product_id", nullable = false)
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 45)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "price", nullable = false, precision = 2)
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Basic
    @Column(name = "description", nullable = false, length = -1)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "last_update", nullable = false)
    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Basic
    @Column(name = "category_id", nullable = false)
    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @Basic
    @Column(name = "image", nullable = true, length = 45)
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Basic
    @Column(name = "thumb_image", nullable = true, length = 45)
    public String getThumbImage() {
        return thumbImage;
    }

    public void setThumbImage(String thumbImage) {
        this.thumbImage = thumbImage;
    }

    @Basic
    @Column(name = "description_detail", nullable = true, length = -1)
    public String getDescriptionDetail() {
        return descriptionDetail;
    }

    public void setDescriptionDetail(String descriptionDetail) {
        this.descriptionDetail = descriptionDetail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductEntity that = (ProductEntity) o;
        return productId == that.productId &&
                categoryId == that.categoryId &&
                Objects.equals(name, that.name) &&
                Objects.equals(price, that.price) &&
                Objects.equals(description, that.description) &&
                Objects.equals(lastUpdate, that.lastUpdate) &&
                Objects.equals(image, that.image) &&
                Objects.equals(thumbImage, that.thumbImage) &&
                Objects.equals(descriptionDetail, that.descriptionDetail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, name, price, description, lastUpdate, categoryId, image, thumbImage, descriptionDetail);
    }
}
