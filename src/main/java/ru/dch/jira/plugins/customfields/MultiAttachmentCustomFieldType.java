package ru.dch.jira.plugins.customfields;

import com.atlassian.jira.issue.AttachmentManager;
import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.issue.customfields.impl.CalculatedCFType;
import com.atlassian.jira.issue.fields.CustomField;
import com.atlassian.jira.issue.fields.layout.field.FieldLayoutItem;

import java.util.List;
import java.util.Map;

/**
 * Created by Jessie on 18.03.14.
 */
public class MultiAttachmentCustomFieldType extends CalculatedCFType
{
    private AttachmentManager attachmentManager = null;

    public MultiAttachmentCustomFieldType(AttachmentManager attachmentManager)
    {
        this.attachmentManager = attachmentManager;
    }

    @Override
    public Map<String, Object> getVelocityParameters(Issue issue, CustomField field, FieldLayoutItem fieldLayoutItem)
    {
        Map<String, Object> params = super.getVelocityParameters(issue,field, fieldLayoutItem);
        params.put("attachments", getValueFromIssue(field, issue));
        return params;
    }

    public Object getValueFromIssue(CustomField customField, Issue issue)
    {
        List attachments = attachmentManager.getAttachments(issue);
        return attachments;
    }

    /** not sure what this does...doesn't ever seem to get called...but must exist */
    public String getStringFromSingularObject(Object customFieldObject)
    {
        return customFieldObject.toString();
    }

    /** not sure what this does...doesn't ever seem to get called...but must exist */
    public Object getSingularObjectFromString(String customFieldObject)
    {
        return customFieldObject;
    }

}
