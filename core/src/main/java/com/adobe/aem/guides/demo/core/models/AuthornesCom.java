package com.adobe.aem.guides.demo.core.models;

import java.util.List;

import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.REQUIRED)
public class AuthornesCom {

    @ValueMapValue
    private String text;
    @ValueMapValue
    private String author;
    @ValueMapValue
    private String dob;

    public String getText() {
        return text;
    }

    public String getAuthorName() {
        return author;
    }

    public String getDob() {
        return dob;
    }

    @ChildResource
    private List<AuthorMul> multifield;

    public List<AuthorMul> getMultifield() {
        return multifield;
    }

    @Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
    public static class AuthorMul {
        @ValueMapValue
        private String honors;
        @ValueMapValue
        private String nationality;

        public String getHonors() {
            return honors;
        }

        public String getNationality() {
            return nationality;
        }

        @ChildResource
        private List<AuthorNestedDiff> nestedfield;

        public List<AuthorNestedDiff> getNestedField() {
            return nestedfield;
        }
    }

    @Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
    public static class AuthorNestedDiff {
        @Inject
        private String education;

        @Inject
        private String writting;

        public String getEducation() {
            return education;
        }

        public String getWriting() {
            return writting;
        }
    }
}
