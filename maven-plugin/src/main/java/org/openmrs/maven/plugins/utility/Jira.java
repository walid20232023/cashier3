package org.openmrs.maven.plugins.utility;

import com.atlassian.jira.rest.client.api.domain.Issue;

public interface Jira {

	Issue getIssue(String issueId);

	String getJiraUrl();
}
