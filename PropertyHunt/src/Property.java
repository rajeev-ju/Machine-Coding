public class Property {
    int propertyId;
    String propertyName;
    String propertyLocation; // e.g. Delhi, Pune, Mumbai
    Long propertyPrice;
    RoomType roomType;
    ListingType listingType;
    String propertySize;  // e.g. 1500 sqft, 1800 sqft
    Status status;
    public int getPropertyId() {
        return propertyId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setPropertyId(int propertyId) {
        this.propertyId = propertyId;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getPropertyLocation() {
        return propertyLocation;
    }

    public void setPropertyLocation(String propertyLocation) {
        this.propertyLocation = propertyLocation;
    }

    public RoomType getPropertyType() {
        return roomType;
    }

    public void setPropertyType(RoomType roomType) {
        this.roomType = roomType;
    }

    public ListingType getListingType() {
        return listingType;
    }

    public Long getPropertyPrice() {
        return propertyPrice;
    }

    public void setPropertyPrice(Long propertyPrice) {
        this.propertyPrice = propertyPrice;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public String getPropertySize() {
        return propertySize;
    }

    public void setPropertySize(String propertySize) {
        this.propertySize = propertySize;
    }

    public void setListingType(ListingType listingType) {
        this.listingType = listingType;
    }
}
