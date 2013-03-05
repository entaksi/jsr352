/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2013, Red Hat, Inc., and individual contributors
 * as indicated by the @author tags. See the copyright.txt file in the
 * distribution for a full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

package org.mybatch.runtime.context;

import javax.batch.operations.JobOperator;

import org.mybatch.job.Split;
import org.mybatch.runtime.SplitExecutionImpl;

public class SplitContextImpl<T> extends AbstractContext<T> {
    private Split split;

    private SplitExecutionImpl splitExecution;

    public SplitContextImpl(Split split, AbstractContext<T>[] outerContexts) {
        super(split.getId(), outerContexts);
        this.split = split;
        this.classLoader = getJobContext().getClassLoader();
        this.splitExecution = new SplitExecutionImpl(split.getId());
        setUpPropertyResolver().resolve(this.split);
        //Split has no listeners
    }

    public Split getSplit() {
        return split;
    }

    public JobOperator.BatchStatus getBatchStatus() {
        return splitExecution.getBatchStatus();
    }

    @Override
    public void setBatchStatus(JobOperator.BatchStatus status) {
        splitExecution.setBatchStatus(status);
    }

    @Override
    public String getExitStatus() {
        return splitExecution.getExitStatus();
    }

    @Override
    public void setExitStatus(String exitStatus) {
        splitExecution.setExitStatus(exitStatus);
    }

    @Override
    public org.mybatch.job.Properties getProperties2() {
        return split.getProperties();
    }

}
