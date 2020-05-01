# Srpring Scheduler Task

## `scheduler`

```
package com.loggar.support.schedulers;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import com.loggar.dao.common.CommonCodeDao;
import com.loggar.service.enrol.EnrollStatusService;
import com.loggar.util.common.SimsConstants;
import com.loggar.util.common.StringUtil;

public class Schedulers {
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	@Autowired private EnrollStatusService enrollStatusService;
	@Autowired private CommonCodeDao commonCodeDao;

	// test scheduler task
	// every min
	// @Scheduled(cron = "10 * * * * *")
	public void testScheduler_min() {
		logger.debug("Test Scheduler Result: ONE-MIN-PASSED.");
	}

	// test scheduler task
	// every day 01:01:00
	@Scheduled(cron = "0 01 01 * * *")
	public void testScheduler() {
		String trimester = commonCodeDao.enrolTrimester();
		logger.info("Test Scheduler Result: ENROL_TRIMESTER IS {}", trimester);
	}

	// clear not-confirmed-enrolled-class for enrol-trimester
	// every day 01:11:00
	@Scheduled(cron = "0 11 01 * * *")
	public void clearEnrolledClassTargets() {
		String trimester = commonCodeDao.enrolTrimester();
		if (StringUtil.isEmpty(trimester)) {
			logger.info("Not valid Enrol Trimester: {}", trimester);
		} else {
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("trimester", trimester);
			paramMap.put("delUid", SimsConstants.SIMS_SCHEDULER);
			int r = enrollStatusService.executeClearEnrolledClassTargets(paramMap);
			logger.info("Not-Confirmed-Enrolled-Classes Cleard: {}", r);
		}
	}
}

```

## application context

```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans" xmlns:beans="http://www.springframework.org/schema/beans" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:aop="http://www.springframework.org/schema/aop" xmlns:context="http://www.springframework.org/schema/context" xmlns:tx="http://www.springframework.org/schema/tx" xmlns:util="http://www.springframework.org/schema/util" xmlns:task="http://www.springframework.org/schema/task"
	xsi:schemaLocation="http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop-3.2.xsd
        http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-3.2.xsd
        http://www.springframework.org/schema/tx http://www.springframework.org/schema/tx/spring-tx-3.2.xsd
        http://www.springframework.org/schema/util http://www.springframework.org/schema/util/spring-util-3.2.xsd
        http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-3.2.xsd
        http://www.springframework.org/schema/task http://www.springframework.org/schema/task/spring-task-3.2.xsd">
	<!-- annotation-driven for Spring Task (@Scheduled) -->
	<task:annotation-driven />
	<bean id="schedulers" class="com.loggar.support.schedulers.Schedulers"></bean>
</beans>
```

