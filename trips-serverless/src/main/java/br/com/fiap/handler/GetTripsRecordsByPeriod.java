package br.com.fiap.handler;

import java.util.List;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import br.com.fiap.dao.TripRepository;
import br.com.fiap.model.*;

public class GetTripsRecordsByPeriod implements RequestHandler<HandlerRequest, HandlerResponse> {

	private final TripRepository repository = new TripRepository();
	
	@Override
	public HandlerResponse handleRequest(HandlerRequest request, Context context) {

		final String country = request.getPathParameters().get("country");
		final String start = request.getQueryStringParameters().get("start");
		final String end = request.getQueryStringParameters().get("end");

		context.getLogger().log("Searching for registered trips between " + start + " and " + end);

		final List<Trip> trips = this.repository.findByPeriod(country,start, end);
		
		if(trips == null || trips.isEmpty()) {
			return HandlerResponse.builder().setStatusCode(404).build();
		}
		
		return HandlerResponse.builder().setStatusCode(200).setObjectBody(trips).build();
	}
}