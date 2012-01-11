package com.hekabe.dashboard.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class NewClusterSwitchToTabTwoEvent extends
		GwtEvent<NewClusterSwitchToTabTwoEventHandler> {
	public static Type<NewClusterSwitchToTabTwoEventHandler> TYPE = new Type<NewClusterSwitchToTabTwoEventHandler>();

	@Override
	public Type<NewClusterSwitchToTabTwoEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(NewClusterSwitchToTabTwoEventHandler handler) {
		handler.onNewClusterSwitchToTabTwo(this);
	}

}
