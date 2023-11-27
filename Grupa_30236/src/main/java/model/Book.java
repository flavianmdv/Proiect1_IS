package model;

// Java Bean -

// POJO - Plain Old Java Object


import java.time.LocalDate;


    public class Book{

        private Long id;

        private String author;

        private String title;

        private LocalDate publishedDate;

        private int pret;

        private int cantitate;

        public int getPret() {
            return pret;
        }

        public void setPret(int pret) {
            this.pret = pret;
        }

        public int getCantitate() {
            return cantitate;
        }

        public void setCantitate(int cantitate) {
            this.cantitate = cantitate;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public LocalDate getPublishedDate() {
            return publishedDate;
        }

        public void setPublishedDate(LocalDate publishedDate) {
            this.publishedDate = publishedDate;
        }

        @Override
        public String toString(){
            return String.format("Id: %d | Title: %s | Author: %s | Date: %s | Cantitate: %d | Pret: %d\n", this.id, this.title, this.author, this.publishedDate, this.cantitate, this.pret);
        }
    }

