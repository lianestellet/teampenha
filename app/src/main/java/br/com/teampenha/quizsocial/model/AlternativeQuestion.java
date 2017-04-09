package br.com.teampenha.quizsocial.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AlternativeQuestion implements Parcelable{

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    public enum Category {
        FREQUENCY("time"),
        PERCENT("percent");
        //NUMERIC("numeric");

        private final String mFormatted;

        Category(String formatted) {
            mFormatted = formatted;
        }

        @JsonCreator
        public static Category fromString(String categoryStr) {
            Category category = null;
            switch (categoryStr) {
                case "time":
                    category = FREQUENCY;
                    break;
                case "percent":
                    category = PERCENT;
                    break;
                /*case "numeric":
                    category = NUMERIC;
                    break;*/
            }

            return category;
        }
    }

    @JsonProperty("desc")
    private String mDescription;

    @JsonProperty("value")
    private double mValue;

    @JsonProperty("category")
    private Category mCategory;

    public AlternativeQuestion() {

    }

    public AlternativeQuestion(String mDescription, double mValue, Category mCategory) {
        this.mDescription = mDescription;
        this.mValue = mValue;
        this.mCategory = mCategory;
    }

    public String getDescription() {
        return mDescription;
    }

    public double getValue() {
        return mValue;
    }

    public Category getCategory() {
        return mCategory;
    }

    protected AlternativeQuestion(Parcel in) {
        mDescription = in.readString();
        mValue = in.readDouble();
    }

    public static final Creator<AlternativeQuestion> CREATOR = new Creator<AlternativeQuestion>() {
        @Override
        public AlternativeQuestion createFromParcel(Parcel in) {
            return new AlternativeQuestion(in);
        }

        @Override
        public AlternativeQuestion[] newArray(int size) {
            return new AlternativeQuestion[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mDescription);
        dest.writeDouble(mValue);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AlternativeQuestion that = (AlternativeQuestion) o;

        return mDescription != null ? mDescription.equals(that.mDescription) : that.mDescription == null;

    }

    @Override
    public int hashCode() {
        return mDescription != null ? mDescription.hashCode() : 0;
    }
}
