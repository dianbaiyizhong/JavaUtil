package com.zm;

import java.util.List;


public class MediaResVo {
    public class Image {

        private String url;


        private Integer width;
        private Integer height;

        public Integer getWidth() {
            return width;
        }

        public void setWidth(Integer width) {
            this.width = width;
        }

        public Integer getHeight() {
            return height;
        }

        public void setHeight(Integer height) {
            this.height = height;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    public class Video {
        private Integer width;
        private Integer height;

        public Integer getWidth() {
            return width;
        }

        public void setWidth(Integer width) {
            this.width = width;
        }

        public Integer getHeight() {
            return height;
        }

        public void setHeight(Integer height) {
            this.height = height;
        }

        private String url;
        private String captureUrl;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getCaptureUrl() {
            return captureUrl;
        }

        public void setCaptureUrl(String captureUrl) {
            this.captureUrl = captureUrl;
        }
    }


    private List<Image> image;

    private List<Video> video;

    public List<Image> getImage() {
        return image;
    }

    public void setImage(List<Image> image) {
        this.image = image;
    }

    public List<Video> getVideo() {
        return video;
    }

    public void setVideo(List<Video> video) {
        this.video = video;
    }
}
