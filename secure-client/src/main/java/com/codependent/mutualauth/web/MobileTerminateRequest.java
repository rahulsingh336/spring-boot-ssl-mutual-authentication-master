package com.codependent.mutualauth.web;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;

public class MobileTerminateRequest{
  private static final long serialVersionUID = 8228180561726531608L;
  @JsonProperty("programId")
  private String programId;

  @JsonProperty("content")
  private String content;

  @JsonProperty("encoding")
  private String encoding;

  @JsonProperty("destination")
  private List<Destination> destination = new ArrayList();

  @JsonProperty("countryCode")
  private String countryCode;

  public MobileTerminateRequest() {
  }

  public String getProgramId() {
    return this.programId;
  }

  public String getContent() {
    return this.content;
  }

  public String getEncoding() {
    return this.encoding;
  }

  public List<Destination> getDestination() {
    return this.destination;
  }

  public String getCountryCode() {
    return this.countryCode;
  }

  public void setProgramId(String programId) {
    this.programId = programId;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public void setEncoding(String encoding) {
    this.encoding = encoding;
  }

  public void setDestination(List<Destination> destination) {
    this.destination = destination;
  }

  public void setCountryCode(String countryCode) {
    this.countryCode = countryCode;
  }

  public boolean equals(Object o) {
    if(o == this) {
      return true;
    } else if(!(o instanceof MobileTerminateRequest)) {
      return false;
    } else {
      MobileTerminateRequest other = (MobileTerminateRequest)o;
      if(!other.canEqual(this)) {
        return false;
      } else {
        label71: {
          Object this$programId = this.getProgramId();
          Object other$programId = other.getProgramId();
          if(this$programId == null) {
            if(other$programId == null) {
              break label71;
            }
          } else if(this$programId.equals(other$programId)) {
            break label71;
          }

          return false;
        }

        Object this$content = this.getContent();
        Object other$content = other.getContent();
        if(this$content == null) {
          if(other$content != null) {
            return false;
          }
        } else if(!this$content.equals(other$content)) {
          return false;
        }

        label57: {
          Object this$encoding = this.getEncoding();
          Object other$encoding = other.getEncoding();
          if(this$encoding == null) {
            if(other$encoding == null) {
              break label57;
            }
          } else if(this$encoding.equals(other$encoding)) {
            break label57;
          }

          return false;
        }

        Object this$destination = this.getDestination();
        Object other$destination = other.getDestination();
        if(this$destination == null) {
          if(other$destination != null) {
            return false;
          }
        } else if(!this$destination.equals(other$destination)) {
          return false;
        }

        Object this$countryCode = this.getCountryCode();
        Object other$countryCode = other.getCountryCode();
        if(this$countryCode == null) {
          if(other$countryCode == null) {
            return true;
          }
        } else if(this$countryCode.equals(other$countryCode)) {
          return true;
        }

        return false;
      }
    }
  }

  protected boolean canEqual(Object other) {
    return other instanceof MobileTerminateRequest;
  }

  public int hashCode() {
    int result = 0;
    Object $programId = this.getProgramId();
    result = result * 59 + ($programId == null?43:$programId.hashCode());
    Object $content = this.getContent();
    result = result * 59 + ($content == null?43:$content.hashCode());
    Object $encoding = this.getEncoding();
    result = result * 59 + ($encoding == null?43:$encoding.hashCode());
    Object $destination = this.getDestination();
    result = result * 59 + ($destination == null?43:$destination.hashCode());
    Object $countryCode = this.getCountryCode();
    result = result * 59 + ($countryCode == null?43:$countryCode.hashCode());
    return result;
  }

  public String toString() {
    return "MobileTerminateRequest(programId=" + this.getProgramId() + ", content=" + this.getContent() + ", encoding=" + this.getEncoding() + ", countryCode=" + this.getCountryCode() + ")";
  }
}
